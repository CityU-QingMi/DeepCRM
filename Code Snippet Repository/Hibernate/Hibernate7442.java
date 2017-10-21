		@Override
		public boolean equals(Object o) {
			if ( this == o ) return true;
			if ( !( o instanceof VehicleId ) ) return false;

			VehicleId vehicleId = (VehicleId) o;

			if ( vehicleVendorNumber != vehicleId.vehicleVendorNumber ) return false;
			return vehicleNumber == vehicleId.vehicleNumber;
		}
