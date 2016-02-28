class Person
{
	String firstName;
	String lastName;
	Gender gender;
	Boolean hasOscar;
	Boolean hasGoldenGlobe;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Boolean getHasOscar() {
		return hasOscar;
	}
	public void setHasOscar(Boolean hasOscar) {
		this.hasOscar = hasOscar;
	}
	public Boolean getHasGoldenGlobe() {
		return hasGoldenGlobe;
	}
	public void setHasGoldenGlobe(Boolean hasGoldenGlobe) {
		this.hasGoldenGlobe = hasGoldenGlobe;
	}
	public String toXMLString() {
		String value = Tools.toXMLTag("firstName", this.firstName);
		value += Tools.toXMLTag("lastName", this.lastName);
		value += Tools.toXMLTag("gender", this.gender.toString());
		value += Tools.toXMLTag("hasOscar", this.hasOscar.toString());
		value += Tools.toXMLTag("hasGoldenGlobe", this.hasGoldenGlobe.toString());
		return Tools.toXMLTag("Person", value);
	}
}