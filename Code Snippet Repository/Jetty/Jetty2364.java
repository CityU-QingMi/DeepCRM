        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            BalancerMember that = (BalancerMember)obj;
            return _name.equals(that._name);
        }
