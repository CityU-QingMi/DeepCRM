	protected void compare(Integer id, Object expected, Object received, String geometryType) {
		assertTrue( expected != null || received == null );
		if ( expected instanceof byte[] ) {
			assertArrayEquals( "Failure on testsuite-suite for case " + id, (byte[]) expected, (byte[]) received );

		}
		else if ( expected instanceof Geometry ) {
			if ( JTS.equals( geometryType ) ) {
				if ( !(received instanceof Geometry) ) {
					fail(
							"Expected a JTS Geometry, but received an object of type " + received.getClass()
									.getCanonicalName()
					);
				}
				assertTrue(
						"Failure on testsuite-suite for case " + id,
						geometryEquality.test( (Geometry) expected, (Geometry) received )
				);
			}
			else {
				if ( !(received instanceof org.geolatte.geom.Geometry) ) {
					fail(
							"Expected a Geolatte Geometry, but received an object of type " + received.getClass()
									.getCanonicalName()
					);
				}
				assertTrue(
						"Failure on testsuite-suite for case " + id,
						geometryEquality.test(
								(Geometry) expected,
								(Geometry) org.geolatte.geom.jts.JTS.to( (org.geolatte.geom.Geometry) received )
						)
				);
			}

		}
		else {
			if ( expected instanceof Long ) {
				assertEquals( "Failure on testsuite-suite for case " + id, ((Long) expected).intValue(), received );
			}
			else {
				assertEquals( "Failure on testsuite-suite for case " + id, expected, received );
			}
		}
	}
