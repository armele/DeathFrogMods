package com.deathfrog.mc.strongerframes;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeathFrogUtils {
	static protected Logger logger = LogManager.getLogger(DFStrongerFrames.MODID);
	
	public static void log(Level level, String msg) {
		logger.log(level, DFStrongerFrames.MODID + ": " + msg);
	}
	
}
