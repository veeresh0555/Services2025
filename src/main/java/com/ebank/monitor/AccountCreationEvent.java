package com.ebank.monitor;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Category;

/***
 * @author Veeresh
 * 
 * @category Custom Java Flight Recorder (JFR) Event class
 * @see Monitoring with Custom Events
 * 
 * @implNote Implementing Java Flight Recorder (JFR) in your use case involves
 *           using the framework to monitor the performance of your Spring Boot
 *           banking application. JFR allows you to collect diagnostic data
 *           about your running application with very little overhead (typically
 *           less than 1%).
 * 
 *           {@systemProperty} java -XX:+FlightRecorder
 *           -XX:StartFlightRecording=duration=0,filename=banking_app_recording.jfr
 *           -jar bankingApp.jar
 * 
 */

@Label("Account creation Event")
@Category("Banking Application")
public class AccountCreationEvent extends Event {
	
	@Label("Username")
	public String username;

	@Label("Generated Account Number")
	public String accountNumber;

	@Label("Duration (ms)")
	public long processingDuration;
}
