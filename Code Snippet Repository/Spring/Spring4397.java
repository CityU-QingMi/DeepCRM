	@Override
	@Nullable
	public Integer getPriority(Object obj) {
		if (obj instanceof Class) {
			return OrderUtils.getPriority((Class<?>) obj);
		}
		Integer priority = OrderUtils.getPriority(obj.getClass());
		if (priority == null && obj instanceof DecoratingProxy) {
			priority = OrderUtils.getOrder(((DecoratingProxy) obj).getDecoratedClass());
		}
		return priority;
	}
