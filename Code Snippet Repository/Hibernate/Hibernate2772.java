		public String format(BigDecimal number) {
			try {
				// TODO : what amount of significant digits need to be supported here?
				//      - from the DecimalFormat docs:
				//          [significant digits] = [minimum integer digits] + [maximum fraction digits]
				DecimalFormat jdkFormatter = new DecimalFormat( FORMAT_STRING );
				jdkFormatter.setMinimumIntegerDigits( 1 );
				jdkFormatter.setMaximumFractionDigits( Integer.MAX_VALUE );
				return jdkFormatter.format( number );
			}
			catch (Throwable t) {
				throw new HibernateException(
						"Unable to format decimal literal in approximate format [" + number.toString() + "]",
						t
				);
			}
		}
