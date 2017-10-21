 	public void testQtz259LW() throws Exception {
 		CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule("0 0 0 LW * ? *");
 		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("test").withSchedule(schedBuilder).build();
 				
 		int i = 0;
 		Date pdate = trigger.getFireTimeAfter(new Date());
 		while (++i < 26) {
 			Date date = trigger.getFireTimeAfter(pdate);
 			System.out.println("fireTime: " + date + ", previousFireTime: " + pdate);
 			assertFalse("Next fire time is the same as previous fire time!", pdate.equals(date));
 			pdate = date;
 		}
 	}
