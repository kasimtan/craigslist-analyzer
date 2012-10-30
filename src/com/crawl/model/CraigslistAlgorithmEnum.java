package com.crawl.model;

import org.apache.log4j.Logger;

import com.crawl.control.Crawler;

/**
 * Which algorithm should be use?
 * @author mschimpf
 *
 */
public enum CraigslistAlgorithmEnum {
	BEST, WORST, HIGHEST, LOWEST, DUMBEST
}