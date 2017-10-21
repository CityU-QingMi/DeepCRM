	public boolean equalsFoo(Foo other) {
		if ( _bytes!=other._bytes ) {
			if ( _bytes==null || other._bytes==null ) return false;
			if ( _bytes.length!=other._bytes.length ) return false;
			for ( int i=0; i< _bytes.length; i++) {
				if ( _bytes[i] != other._bytes[i] ) return false;
			}
		}

		return ( this._bool == other._bool )
		&& ( ( this._boolean == other._boolean ) || ( this._boolean.equals(other._boolean) ) )
		&& ( ( this._byte == other._byte ) || ( this._byte.equals(other._byte) ) )
		//&& ( ( this._date == other._date ) || ( this._date.getDate() == other._date.getDate() && this._date.getMonth() == other._date.getMonth() && this._date.getYear() == other._date.getYear() ) )
		&& ( ( this._double == other._double ) || ( this._double.equals(other._double) ) )
		&& ( ( this._float == other._float ) || ( this._float.equals(other._float) ) )
		&& ( this._int == other._int )
		&& ( ( this._integer == other._integer ) || ( this._integer.equals(other._integer) ) )
		&& ( ( this._long == other._long ) || ( this._long.equals(other._long) ) )
		&& ( this._null == other._null )
		&& ( ( this._short == other._short ) || ( this._short.equals(other._short) ) )
		&& ( ( this._string == other._string) || ( this._string.equals(other._string) ) )
		//&& ( ( this._timestamp==other._timestamp) || ( this._timestamp.getDate() == other._timestamp.getDate() && this._timestamp.getYear() == other._timestamp.getYear() && this._timestamp.getMonth() == other._timestamp.getMonth() ) )
		&& ( this._zero == other._zero )
		&& ( ( this._foo == other._foo ) || ( this._foo.getKey().equals( other._foo.getKey() ) ) )
		&& ( ( this.blob == other.blob ) || ( this.blob.equals(other.blob) ) )
		&& ( this.yesno == other.yesno )
		&& ( ( this.binary == other.binary ) || java.util.Arrays.equals(this.binary, other.binary) )
		&& ( this.key.equals(other.key) )
		&& ( this.theLocale.equals(other.theLocale) )
		&& ( ( this.custom == other.custom ) || ( this.custom[0].equals(other.custom[0]) && this.custom[1].equals(other.custom[1]) ) );

	}
