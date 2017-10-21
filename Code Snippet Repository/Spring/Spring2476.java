	public static ModelMBeanNotificationInfo convertToModelMBeanNotificationInfo(ManagedNotification notificationInfo) {
		String[] notifTypes = notificationInfo.getNotificationTypes();
		if (ObjectUtils.isEmpty(notifTypes)) {
			throw new IllegalArgumentException("Must specify at least one notification type");
		}

		String name = notificationInfo.getName();
		if (!StringUtils.hasText(name)) {
			throw new IllegalArgumentException("Must specify notification name");
		}

		String description = notificationInfo.getDescription();
		return new ModelMBeanNotificationInfo(notifTypes, name, description);
	}
