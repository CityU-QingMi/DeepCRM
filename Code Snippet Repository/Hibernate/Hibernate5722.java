	private Set<PurchaseOrg> getPurchaseOrgsByFacilityId(Long facilityId, EntityManager em) {
		Set<PurchaseOrg> orgs = new HashSet<>();
		try {
			for ( PurchaseOrg purchaseOrg : findAll( PurchaseOrg.class, em ) ) {
				for ( Facility facility : purchaseOrg.getFacilities() ) {
					if ( facility.getId().equals( facilityId ) ) {
						orgs.add( purchaseOrg );
						break;
					}
				}
			}
		}
		catch (Exception e) {

		}
		finally {
			return orgs;
		}
	}
