	@Test
	public void testHashCode() {
		JobDetailImpl job = new JobDetailImpl();
		Assert.assertThat(job.hashCode(), Matchers.is(0));
		
		job.setName("test");
		Assert.assertThat(job.hashCode(), Matchers.not(Matchers.is(0)));
		
		job.setGroup("test");
		Assert.assertThat(job.hashCode(), Matchers.not(Matchers.is(0)));
	}
