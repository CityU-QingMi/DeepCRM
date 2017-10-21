      @Override
      public String toString() {
         final StringBuilder sb = new StringBuilder("Condition{");
         sb.append("source=").append(source);
         sb.append(", predicate=").append(predicate);
         sb.append(", success=").append(success);
         sb.append(", action=").append(action);
         sb.append('}');
         return sb.toString();
      }
