package cz.fi.muni.pv243.eshop.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Security {
	protected static Logger log = Logger.getAnonymousLogger();

	public static String sha2(String password, Integer salt) {

		StringBuilder sb = new StringBuilder();

		if (Integer.signum(salt) == -1)
			salt *= -1;
		else if (Integer.signum(salt) == 0)
			salt = Integer.MAX_VALUE - 3;

		String hex = Integer.toHexString(salt);
		sb.append(hex);
		sb.append("$");

		try {
			// compute digest
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] b = md.digest((password + salt).getBytes());

			// output digest
			for (int i = 0; i < b.length; i++) {
				if ((0xff & b[i]) < 0x10) {
					sb.append("0" + Integer.toHexString((0xFF & b[i])));
				} else {
					sb.append(Integer.toHexString(0xFF & b[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {

			log.warning("Unsupported cryptographic operation, you cannot log in");
		}

		return sb.toString();
	}
}
