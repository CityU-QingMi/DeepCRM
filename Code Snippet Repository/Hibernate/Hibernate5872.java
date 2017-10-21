	@Test
	@TestForIssue(jiraKey = "")
	public void testCollectionSizeLoadedWithGraph() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			 Student student1 = new Student();
			 student1.setId( 1 );
			 student1.setName( "Student 1" );
			 Student student2 = new Student();
			 student2.setId( 2 );
			 student2.setName( "Student 2" );

			 Course course1 = new Course();
			 course1.setName( "Full Time" );
			 Course course2 = new Course();
			 course2.setName( "Part Time" );

			 Set<Course> std1Courses = new HashSet<Course>();
			 std1Courses.add( course1 );
			 std1Courses.add( course2 );
			 student1.setCourses( std1Courses );

			 Set<Course> std2Courses = new HashSet<Course>();
			 std2Courses.add( course2 );
			 student2.setCourses( std2Courses );

			 entityManager.persist( student1 );
			 entityManager.persist( student2 );

		});

		doInJPA( this::entityManagerFactory, entityManager -> {
			EntityGraph<?> graph = entityManager.getEntityGraph( "Student.Full" );

			List<Student> students = entityManager.createNamedQuery( "LIST_OF_STD", Student.class )
					.setHint( QueryHints.HINT_FETCHGRAPH, graph )
					.getResultList();

			assertEquals( 2, students.size() );
		});
	}
