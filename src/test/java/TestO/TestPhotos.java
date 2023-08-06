package TestO;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import owner.*;


public class TestPhotos {
	ArrayList<Announce> A=new ArrayList<Announce>();
	Announce b;
	ArrayList<Image>c=new ArrayList<Image>();
	User e=new User();
	@Given("I am a registered as owner on Sakancom")
	public void i_am_a_registered_as_owner_on_sakancom() {
		 assertTrue(e.isOwner()==true);
	}
	@Given("I am logged in to my account")
	public void i_am_logged_in_to_my_account() {
		assertTrue(e.isOwner()==true);
	}
	@When("want to create or edit exists residence")
	public void want_to_create_or_edit_exists_residence() {
		int a;
		a=A.size();
	    b=new Announce(null, null, null, null, 0, false, null);
		A.add(b);
		assertTrue(A.size()==a+1);
	}
	@When("I upload photos of my private residence")
	public void i_upload_photos_of_my_private_residence() {
		Image d;
		try {
			d =ImageIO.read(new File("D:\\UB\\summer 2023\\Softwre Engineering\\images.jpg"));
			c.add(d);
			int indx=c.size()-1;
			assertTrue(c.get(indx).equals(d)==true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("picture is not found");
			assertFalse(true);
		}
		
	}
	@Then("the photos should be added to the residence announcement")
	public void the_photos_should_be_added_to_the_residence_announcement() {
		b.setPhotos(c);
		A.add(b);
		int index=A.size()-1;
		
		assertTrue(A.get(index).getPhotos()==c);
	}


}