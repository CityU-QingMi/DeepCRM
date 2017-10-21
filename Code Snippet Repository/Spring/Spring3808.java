		@Override
		public void setName(String nam) throws RemoteException {
			if (nam != null && nam.endsWith("Exception")) {
				RemoteException rex;
				try {
					Class<?> exClass = Class.forName(nam);
					Constructor<?> ctor = exClass.getConstructor(String.class);
					rex = (RemoteException) ctor.newInstance("myMessage");
				}
				catch (Exception ex) {
					throw new RemoteException("Illegal exception class name: " + nam, ex);
				}
				throw rex;
			}
			name = nam;
		}
