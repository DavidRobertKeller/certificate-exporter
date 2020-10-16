package davidkeller.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.util.Enumeration;

import java.security.cert.X509Certificate;

public class CertificateExporter {

	public static void main(String[] args) throws Exception {
		KeyStore keystore = KeyStore.getInstance("Windows-MY");
		keystore.load(null, null);
		Enumeration<String> aliases = keystore.aliases();

		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();
			File file = new File("./" + alias + ".crt");
			System.out.println("alias " + alias + ", public certificate saved in " + file.getAbsolutePath());
			try (FileOutputStream fos = new FileOutputStream(file)) {
				X509Certificate certificate = (X509Certificate) keystore.getCertificate(alias);
				fos.write(certificate.getEncoded());
			}
		}
	}
}
