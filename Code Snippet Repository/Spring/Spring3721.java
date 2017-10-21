	@Override
	public MBeanInfo getMBeanInfo() {
		MBeanAttributeInfo attr = new MBeanAttributeInfo("name", "java.lang.String", "", true, false, false);
		return new MBeanInfo(
				TestDynamicMBean.class.getName(), "",
				new MBeanAttributeInfo[]{attr},
				new MBeanConstructorInfo[0],
				new MBeanOperationInfo[0],
				new MBeanNotificationInfo[0]);
	}
