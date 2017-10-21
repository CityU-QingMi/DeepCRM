    @Test
    public void generatesCurrentTimestamp() {
        doInJPA(this::entityManagerFactory, entityManager -> {
            Event event = new Event();
            entityManager.persist(event);
            entityManager.flush();
            Assert.assertNotNull(event.getDate());
            Assert.assertNotNull(event.getCalendar());
            Assert.assertNotNull(event.getSqlDate());
            Assert.assertNotNull(event.getTime());
            Assert.assertNotNull(event.getTimestamp());
            Assert.assertNotNull(event.getInstant());
            Assert.assertNotNull(event.getLocalDate());
            Assert.assertNotNull(event.getLocalDateTime());
            Assert.assertNotNull(event.getLocalTime());
            Assert.assertNotNull(event.getMonthDay());
            Assert.assertNotNull(event.getOffsetDateTime());
            Assert.assertNotNull(event.getOffsetTime());
            Assert.assertNotNull(event.getYear());
            Assert.assertNotNull(event.getYearMonth());
            Assert.assertNotNull(event.getZonedDateTime());
        });
    }
