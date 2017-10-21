    public void testAnnotationDetection() {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName("hi");

        jobDetail.setJobClass(SomePersistentJob.class);
        assertTrue("Expecting SomePersistentJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertFalse("Expecting SomePersistentJob to not disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeNonConcurrentJob.class);
        assertFalse("Expecting SomeNonConcurrentJob to not be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeNonConcurrentJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeNonConcurrentPersistentJob.class);
        assertTrue("Expecting SomeNonConcurrentPersistentJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeNonConcurrentPersistentJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeStatefulJob.class);
        assertTrue("Expecting SomeStatefulJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeStatefulJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeExtendedPersistentJob.class);
        assertTrue("Expecting SomeExtendedPersistentJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertFalse("Expecting SomeExtendedPersistentJob to not disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeExtendedNonConcurrentJob.class);
        assertFalse("Expecting SomeExtendedNonConcurrentJob to not be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeExtendedNonConcurrentJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeExtendedNonConcurrentPersistentJob.class);
        assertTrue("Expecting SomeExtendedNonConcurrentPersistentJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeExtendedNonConcurrentPersistentJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());

        jobDetail.setJobClass(SomeExtendedStatefulJob.class);
        assertTrue("Expecting SomeExtendedStatefulJob to be persistent", jobDetail.isPersistJobDataAfterExecution());
        assertTrue("Expecting SomeExtendedStatefulJob to disallow concurrent execution", jobDetail.isConcurrentExectionDisallowed());
    }
