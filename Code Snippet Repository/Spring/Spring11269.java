	@Nullable
	private Object applyConversion(@Nullable Object value, NamedValueInfo namedValueInfo, MethodParameter parameter,
			BindingContext bindingContext, ServerWebExchange exchange) {

		WebDataBinder binder = bindingContext.createDataBinder(exchange, namedValueInfo.name);
		try {
			value = binder.convertIfNecessary(value, parameter.getParameterType(), parameter);
		}
		catch (ConversionNotSupportedException ex) {
			throw new ServerErrorException("Conversion not supported.", parameter, ex);
		}
		catch (TypeMismatchException ex) {
			throw new ServerWebInputException("Type mismatch.", parameter, ex);
		}
		return value;
	}
