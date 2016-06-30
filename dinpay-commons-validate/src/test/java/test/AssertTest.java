package test;

import net.sf.oval.constraint.EqualToField;

import org.junit.Ignore;
import org.junit.Test;

import common.utils.validate.ValidateException;
import common.utils.validate.ValidateHelper;
import common.utils.validate.constraint.NotEmptyNull;

public class AssertTest {

	@Test
	@Ignore
	public void test() {
		try {
			// ValidateHelper.assertNull("K不为空", "");
			// ValidateHelper.assertNotNull("K为空", "");
			// ValidateHelper.assertIn("值应该为A,B,C", "D", "A","B","C");
			// ValidateHelper.assertEquals("值应该为A", "A", "B");
			// ValidateHelper.assertTrue("1!=2", 1==2);

			Mo mo = new Mo();
			Ko ko = new Ko();
			// ko.setKoname("d");
			mo.setKo(ko);
			ValidateHelper.asertPass(mo);
		} catch (ValidateException e) {
			System.out.println(e.getCode() + ":" + e.getMessage());
		}
	}

	@Test
	public void testExtend() {
		CHo ho = new CHo();
		ho.setName("0");
		ValidateHelper.asertPass(ho);
	}

	public static class Po {
		@NotEmptyNull(message = "Po name 不能为空")
		private String name;

		@NotEmptyNull(message = "Po ko 不能为空")
		private String ko;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKo() {
			return ko;
		}

		public void setKo(String ko) {
			this.ko = ko;
		}

	}

	public static class CHo extends Po {
		@NotEmptyNull(message = "cho name 不能为空")
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class Mo {
		@NotEmptyNull(profiles = "V1")
		private String name = "3";

		@EqualToField(value = "name", profiles = "V2")
		private String name1 = "3";

		@NotEmptyNull.List( { @NotEmptyNull(message = "ko 不能为空"), @NotEmptyNull(errorCode = "10993", message = "koname 不能为空", target = "koname"), })
		private Ko ko;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName1() {
			return name1;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public Ko getKo() {
			return ko;
		}

		public void setKo(Ko ko) {
			this.ko = ko;
		}

	}

	public static class Ko {

		private String koname;

		private int state;

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getKoname() {
			return koname;
		}

		public void setKoname(String koname) {
			this.koname = koname;
		}

	}
}
