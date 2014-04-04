/* Copyright (c) 2011 Danish Maritime Authority.
 *
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
package net.maritimecloud.internal.message.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.maritimecloud.core.message.Message;

/**
 * Various utility method that are used by generated messages.
 * 
 * @author Kasper Nielsen
 */
public class MessageHelper {

    public static <T extends Message<T>> T immutable(T t) {
        return t == null ? null : t.immutable();
    }

    public static <T> List<T> immutableCopy(List<? extends T> list) {
        ArrayList<T> l = new ArrayList<>(list.size());
        for (T t : list) {
            l.add(convert(t));
        }
        return Collections.unmodifiableList(l);
    }

    public static <K, V> Map<K, V> immutableCopy(Map<? extends K, ? extends V> map) {
        LinkedHashMap<K, V> l = new LinkedHashMap<>(map.size());
        for (Map.Entry<? extends K, ? extends V> t : map.entrySet()) {
            l.put(convert(t.getKey()), convert(t.getValue()));
        }
        return Collections.unmodifiableMap(l);
    }

    public static <T> Set<T> immutableCopy(Set<? extends T> list) {
        LinkedHashSet<T> l = new LinkedHashSet<>(list.size());
        for (T t : list) {
            l.add(convert(t));
        }
        return Collections.unmodifiableSet(l);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> T convert(T value) {
        return value instanceof Message ? (T) ((Message) value).immutable() : value;
    }
}