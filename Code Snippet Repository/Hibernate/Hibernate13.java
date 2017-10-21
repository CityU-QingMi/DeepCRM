		public void removeAddress(Address address) {
			for ( Iterator<PersonAddress> iterator = addresses.iterator(); iterator.hasNext(); ) {
				PersonAddress personAddress = iterator.next();
				if(personAddress.getPerson().equals(this) &&
						personAddress.getAddress().equals(address)) {
					iterator.remove();
					personAddress.getAddress().getOwners().remove(personAddress);
					personAddress.setPerson(null);
					personAddress.setAddress(null);
				}
			}
		}
