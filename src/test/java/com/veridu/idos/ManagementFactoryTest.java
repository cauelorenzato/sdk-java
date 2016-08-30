package com.veridu.idos;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.veridu.idos.ManagementFactory;
import com.veridu.idos.endpoints.management.Credentials;
import com.veridu.idos.endpoints.management.Members;
import com.veridu.idos.endpoints.management.Settings;
import com.veridu.idos.exceptions.InvalidToken;

public class ManagementFactoryTest {

    @Test
    public void testGetCredential() throws InvalidToken {
        ManagementFactory factory = new ManagementFactory("token");
        assertNull(factory.credential);
        factory.getCredential();
        assertTrue(factory.credential instanceof Credentials);
    }

    @Test
    public void testGetSetting() throws InvalidToken {
        ManagementFactory factory = new ManagementFactory("token");
        assertNull(factory.setting);
        factory.getSetting();
        assertTrue(factory.setting instanceof Settings);
    }

    @Test
    public void testGetMember() throws InvalidToken {
        ManagementFactory factory = new ManagementFactory("token");
        assertNull(factory.member);
        factory.getMember();
        assertTrue(factory.member instanceof Members);
    }

}
