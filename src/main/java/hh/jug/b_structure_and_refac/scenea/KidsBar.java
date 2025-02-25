package hh.jug.b_structure_and_refac.scenea;

public class KidsBar implements Venue{
	@Override
	public boolean canEnter(Person p) {
		return p.age() < 18;
	}
}
