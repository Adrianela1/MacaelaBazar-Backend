	package com.macaela.api.models.user;

	public class DatosRegistroUsuario {
		private String name;
	    private String email;
	    private Integer age;
	    private String password;
	    private String password2;
	    private boolean administrator;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPassword2() {
			return password2;
		}
		public void setPassword2(String password2) {
			this.password2 = password2;
		}
		public boolean isAdministrator() {
			return administrator;
		}
		public void setAdministrator(boolean administrator) {
			this.administrator = administrator;
		}

	    public DatosRegistroUsuario() {
			super();
		}
		public DatosRegistroUsuario(String name, String email, Integer age, String password, String password2,
				boolean administrator) {
			super();
			this.name = name;
			this.email = email;
			this.age = age;
			this.password = password;
			this.password2 = password2;
			this.administrator = administrator;
		}
	    
	    
}
	

