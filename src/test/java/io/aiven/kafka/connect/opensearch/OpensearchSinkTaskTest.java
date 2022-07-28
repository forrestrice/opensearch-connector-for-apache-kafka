/*
 * Copyright 2020 Aiven Oy
 * Copyright 2016 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiven.kafka.connect.opensearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpensearchSinkTaskTest {

    @Test
    void convertTopicToIndexName() {

        final var longTopicName = "a".repeat(260);
        assertEquals("a".repeat(255), OpensearchSinkTask.convertTopicToIndexName(longTopicName));

        final var colonTopicName = "a:b:c";
        assertEquals("a_b_c", OpensearchSinkTask.convertTopicToIndexName(colonTopicName));

        final var minusTopicName = "-minusTopicName";
        assertEquals("minustopicname", OpensearchSinkTask.convertTopicToIndexName(minusTopicName));

        final var plusTopicName = "+plusTopicName";
        assertEquals("plustopicname", OpensearchSinkTask.convertTopicToIndexName(plusTopicName));

        final var underscoreTopicName = "_underscoreTopicName";
        assertEquals("underscoretopicname", OpensearchSinkTask.convertTopicToIndexName(underscoreTopicName));

        final var dotTopicName = ".";
        assertEquals("dot", OpensearchSinkTask.convertTopicToIndexName(dotTopicName));

        final var dotDotTopicName = "..";
        assertEquals("dotdot", OpensearchSinkTask.convertTopicToIndexName(dotDotTopicName));

    }

}
