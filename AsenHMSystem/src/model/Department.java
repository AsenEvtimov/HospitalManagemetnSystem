package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
		private int deptId;
		@Column
		private String departmentName;
			
		public Department() {}

		public Department(String departmentName) {
			super();
			this.departmentName = departmentName;
		}

		public Department(int depId, String departmentName) {
			super();
			this.deptId = depId;
			this.departmentName = departmentName;
			
		}



		public int getDeptId() {
			return deptId;
		}

		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
			result = prime * result + deptId;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Department other = (Department) obj;
			if (departmentName == null) {
				if (other.departmentName != null)
					return false;
			} else if (!departmentName.equals(other.departmentName))
				return false;
			if (deptId != other.deptId)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Department [depId=" + deptId + ", departmentName=" + departmentName + "]";
		}
		
}
