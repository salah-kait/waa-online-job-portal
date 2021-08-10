package com.MIU.OnlineJob.System;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHelper.class.getName());
	private static PropertiesHelper propertiesHelper = null;

	public Properties properties = new Properties();

	private PropertiesHelper() {
		try (InputStream propertiesStream = getClass().getResourceAsStream("/application.properties")) {
			properties.load(propertiesStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public static PropertiesHelper getInstance() {
		if (propertiesHelper == null)
			propertiesHelper = new PropertiesHelper();

		return propertiesHelper;
	}
}
