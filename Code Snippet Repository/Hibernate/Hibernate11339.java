	public int hashCode() {
		int result;
		result = (id != null ? id.hashCode() : 0);
		result = 31 * result + (data1 != null ? data1.hashCode() : 0);
		result = 31 * result + (data2 != null ? data2.hashCode() : 0);
		result = 31 * result + (number1 != null ? number1.hashCode() : 0);
		result = 31 * result + (number2 != null ? number2.hashCode() : 0);
		result = 31 * result + (date1 != null ? date1.hashCode() : 0);
		return result;
	}
