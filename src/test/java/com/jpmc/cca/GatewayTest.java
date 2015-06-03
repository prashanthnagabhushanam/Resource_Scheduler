package com.jpmc.cca;

import org.junit.Assert;
import org.junit.Test;

import com.jpmc.cca.util.Gateway;

public class GatewayTest {

	@Test
	public void validateObject()
	{
		Gateway gateway = Gateway.getGateway();
		Assert.assertTrue(true);
	}
}
