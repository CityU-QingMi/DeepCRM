	private ModelMBeanNotificationInfo[] extractNotificationMetadata(Object mapValue) {
		if (mapValue instanceof ManagedNotification) {
			ManagedNotification mn = (ManagedNotification) mapValue;
			return new ModelMBeanNotificationInfo[] {JmxMetadataUtils.convertToModelMBeanNotificationInfo(mn)};
		}
		else if (mapValue instanceof Collection) {
			Collection<?> col = (Collection<?>) mapValue;
			List<ModelMBeanNotificationInfo> result = new ArrayList<>();
			for (Object colValue : col) {
				if (!(colValue instanceof ManagedNotification)) {
					throw new IllegalArgumentException(
							"Property 'notificationInfoMappings' only accepts ManagedNotifications for Map values");
				}
				ManagedNotification mn = (ManagedNotification) colValue;
				result.add(JmxMetadataUtils.convertToModelMBeanNotificationInfo(mn));
			}
			return result.toArray(new ModelMBeanNotificationInfo[result.size()]);
		}
		else {
			throw new IllegalArgumentException(
					"Property 'notificationInfoMappings' only accepts ManagedNotifications for Map values");
		}
	}
