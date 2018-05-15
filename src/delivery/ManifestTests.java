package delivery;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManifestTests {

	Manifest myManifest;
	
	@Before
	public void setUp() {
		myManifest = null;
	}

	@Test
	public void testConstruction() {
		myManifest = new Manifest();
	}

}
