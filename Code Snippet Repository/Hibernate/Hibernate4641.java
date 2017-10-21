	@Override
	@SuppressWarnings( {""})
	public synchronized void destroy() {
		if ( active.compareAndSet( true, false ) ) {
			try {
				//First thing, make sure that the fast path read is disabled so that
				//threads not owning the synchronization lock can't get an invalid Service:
				initializedServiceByRole.clear();
				synchronized (serviceBindingList) {
					ListIterator<ServiceBinding> serviceBindingsIterator = serviceBindingList.listIterator(
							serviceBindingList.size()
					);
					while ( serviceBindingsIterator.hasPrevious() ) {
						final ServiceBinding serviceBinding = serviceBindingsIterator.previous();
						serviceBinding.getLifecycleOwner().stopService( serviceBinding );
					}
					serviceBindingList.clear();
				}
				serviceBindingMap.clear();
			}
			finally {
				parent.deRegisterChild( this );
			}
		}
	}
