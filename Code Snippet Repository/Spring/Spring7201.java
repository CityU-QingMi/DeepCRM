	@Nullable
	private Object findAnnotation(MethodParameter returnType) {
		Annotation[] anns = new Annotation[4];
		anns[0] = AnnotatedElementUtils.findMergedAnnotation(returnType.getExecutable(), SendToUser.class);
		anns[1] = AnnotatedElementUtils.findMergedAnnotation(returnType.getExecutable(), SendTo.class);
		anns[2] = AnnotatedElementUtils.findMergedAnnotation(returnType.getDeclaringClass(), SendToUser.class);
		anns[3] = AnnotatedElementUtils.findMergedAnnotation(returnType.getDeclaringClass(), SendTo.class);

		if (anns[0] != null && !ObjectUtils.isEmpty(((SendToUser) anns[0]).value())) {
			return anns[0];
		}
		if (anns[1] != null && !ObjectUtils.isEmpty(((SendTo) anns[1]).value())) {
			return anns[1];
		}
		if (anns[2] != null && !ObjectUtils.isEmpty(((SendToUser) anns[2]).value())) {
			return anns[2];
		}
		if (anns[3] != null && !ObjectUtils.isEmpty(((SendTo) anns[3]).value())) {
			return anns[3];
		}

		for (int i=0; i < 4; i++) {
			if (anns[i] != null) {
				return anns[i];
			}
		}

		return null;
	}
