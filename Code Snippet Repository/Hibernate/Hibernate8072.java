	@Test
	@TestForIssue( jiraKey = "" )
	public void testDeleteMultipleWhereIns() {
		Session s = openSession();
		s.getTransaction().begin();
        s.createQuery("DELETE FROM Panel panelEntity WHERE " +
		        " panelEntity.clientId IN ( SELECT trtPanel.clientId FROM TrtPanel trtPanel ) " +
		        " AND panelEntity.deltaStamp NOT IN ( SELECT trtPanel.deltaStamp FROM TrtPanel trtPanel )").executeUpdate();
        s.getTransaction().commit();
        s.close();
	}
