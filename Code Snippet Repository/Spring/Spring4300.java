		public static boolean isOptional(MethodParameter param) {
			Method method = param.getMethod();
			Constructor<?> ctor = param.getConstructor();
			int index = param.getParameterIndex();
			if (method != null && index == -1) {
				KFunction<?> function = ReflectJvmMapping.getKotlinFunction(method);
				return (function != null && function.getReturnType().isMarkedNullable());
			}
			else {
				KFunction<?> function = null;
				if (method != null) {
					function = ReflectJvmMapping.getKotlinFunction(method);
				}
				else if (ctor != null) {
					function = ReflectJvmMapping.getKotlinFunction(ctor);
				}
				if (function != null) {
					List<KParameter> parameters = function.getParameters();
					KParameter parameter = parameters
							.stream()
							.filter(p -> KParameter.Kind.VALUE.equals(p.getKind()))
							.collect(Collectors.toList())
							.get(index);
					return (parameter.getType().isMarkedNullable() || parameter.isOptional());
				}
			}
			return false;
		}
