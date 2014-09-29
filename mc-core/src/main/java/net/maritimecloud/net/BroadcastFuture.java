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
package net.maritimecloud.net;

import net.maritimecloud.util.Binary;
import net.maritimecloud.util.Timestamp;
import net.maritimecloud.util.geometry.Position;

/**
 * A future returned when sending a broadcast. This future can be used if supported by the underlying transport
 * mechanism.
 *
 * @author Kasper Nielsen
 */
public interface BroadcastFuture {

    /**
     * Returns a unique hash of the broadcast message that was send.
     *
     * @return a unique hash of the broadcast message that was send
     */
    Binary getMessageHash();

    /**
     * Returns an optional position that was attached to the broadcast being sent. Clients that do not have a position
     * reader attached will not attach a position to the header of the broadcast.
     *
     * @return the optional position that was attached to the broadcast being sent
     */
    Position getPosition();

    /**
     * Returns the time stamp that was attached to the broadcast being sent.
     *
     * @return the time stamp that was attached to the broadcast being sent
     */
    Timestamp getTime();

    /**
     * If the protocol in which the broadcast is send uses a central relaying server, for example MMS. This method can
     * be used to determine when the message have been received by the MMS server. This is done on a best effort basis.
     * The broadcast message might be delivered to remote clients before the returned future completes.
     *
     * @return an acknowledgement object that can be used to execute actions when the broadcast has been delivered to a
     *         relay server
     *
     * @throws UnsupportedOperationException
     *             if the underlying communication mechanism does not make of a central relaying server.
     */
    Acknowledgement relayed();
}
