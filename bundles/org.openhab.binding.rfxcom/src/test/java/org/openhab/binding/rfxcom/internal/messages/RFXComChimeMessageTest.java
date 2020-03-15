/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.rfxcom.internal.messages;

import static org.junit.Assert.assertEquals;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.util.HexUtils;
import org.junit.Test;
import org.openhab.binding.rfxcom.internal.exceptions.RFXComException;
import org.openhab.binding.rfxcom.internal.messages.RFXComChimeMessage.SubType;

/**
 * Test for RFXCom-binding
 *
 * @author Martin van Wingerden - Initial contribution
 * @author Mike Jagdis - Added actual functional tests
 */
@NonNullByDefault
public class RFXComChimeMessageTest {

    @Test
    public void testSomeMessages() throws RFXComException {
        String hexMessage = "0716020900A1F350";
        byte[] message = HexUtils.hexToBytes(hexMessage);
        RFXComChimeMessage msg = (RFXComChimeMessage) RFXComMessageFactory.createMessage(message);
        assertEquals("SubType", SubType.SELECTPLUS, msg.subType);
        assertEquals("Seq Number", 9, msg.seqNbr);
        assertEquals("Sensor Id", "41459", msg.getDeviceId());
        assertEquals("Signal Level", 5, msg.signalLevel);

        byte[] decoded = msg.decodeMessage();

        assertEquals("Message converted back", hexMessage, HexUtils.bytesToHex(decoded));
    }

    @Test
    public void testByronBy() throws RFXComException {
        String hexMessage = "071603537E228670";
        byte[] message = HexUtils.hexToBytes(hexMessage);
        RFXComChimeMessage msg = (RFXComChimeMessage) RFXComMessageFactory.createMessage(message);
        assertEquals("SubType", SubType.BYRONBY, msg.subType);
        assertEquals("Seq Number", 83, msg.seqNbr);
        assertEquals("Sensor Id", "64581", msg.getDeviceId());
        assertEquals("Signal Level", 7, msg.signalLevel);
        assertEquals("Sound", 6, msg.chimeSound);

        byte[] decoded = msg.decodeMessage();

        assertEquals("Message converted back", hexMessage, HexUtils.bytesToHex(decoded));
    }
}
