/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.trino.plugin.rocketmq.schema;

import com.google.common.collect.ImmutableMap;
import io.trino.spi.connector.ConnectorSession;
import io.trino.spi.connector.SchemaTableName;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class MapBasedTableDescriptionSupplier implements TableDescriptionSupplier {
    private final Map<SchemaTableName, RocketMQTopicDescription> map;

    public MapBasedTableDescriptionSupplier(Map<SchemaTableName, RocketMQTopicDescription> map)
    {
        this.map = ImmutableMap.copyOf(requireNonNull(map, "map is null"));
    }

    @Override
    public Set<SchemaTableName> listTables()
    {
        return map.keySet();
    }

    @Override
    public Optional<RocketMQTopicDescription> getTopicDescription(ConnectorSession session, SchemaTableName schemaTableName)
    {
        return Optional.ofNullable(map.get(schemaTableName));
    }
}