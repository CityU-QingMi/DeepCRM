	public static CtMethod addSetter(CtClass target, String field, String name) {
		CtField actualField = null;
		try {
			actualField = target.getField( field );
			log.debugf( "Writing setter method [%s] into [%s] for field [%s]", name, target.getName(), field );
			CtMethod method = CtNewMethod.setter( name, actualField );
			target.addMethod( method );
			return method;
		}
		catch (CannotCompileException cce) {
			try {
				// Fall back to create a getter from delegation.
				CtMethod method = CtNewMethod.delegator( CtNewMethod.setter( name, actualField ), target );
				target.addMethod( method );
				return method;
			}
			catch (CannotCompileException ignored) {
				String msg = String.format( "Could not enhance class [%s] to add method [%s] for field [%s]", target.getName(), name, field );
				throw new EnhancementException( msg, cce );
			}
		}
		catch (NotFoundException nfe) {
			String msg = String.format( "Could not enhance class [%s] to add method [%s] for field [%s]", target.getName(), name, field );
			throw new EnhancementException( msg, nfe );
		}
	}
