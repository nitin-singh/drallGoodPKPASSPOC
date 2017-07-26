package drallgoodpkpasspoc
/*

import de.brendamour.jpasskit.PKBarcode
import de.brendamour.jpasskit.PKField
import de.brendamour.jpasskit.PKLocation
import de.brendamour.jpasskit.PKPass
import de.brendamour.jpasskit.enums.PKBarcodeFormat
import de.brendamour.jpasskit.passes.PKGenericPass
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil
import de.brendamour.jpasskit.signing.PKInMemorySigningUtil
import de.brendamour.jpasskit.signing.PKPassTemplateFolder
import de.brendamour.jpasskit.signing.PKSigningInformation
import de.brendamour.jpasskit.signing.PKSigningInformationUtil
import de.brendamour.jpasskit.signing.PKSigningUtil
import grails.io.IOUtils
import org.apache.commons.io.IOUtils

import java.nio.charset.Charset
*/

class PkPassController {
    /*def test() {
        String appleWWDRCA = "/home/nitin/Downloads/AppleWWDRCA.cer"; // this is apple's developer relation cert
        String privateKeyPath = "/home/nitin/Downloads/Certificates.p12"; // the private key you exported from keychain
        String privateKeyPassword = ""; // the password you used to export
        try {

            PKSigningInformation pkSigningInformation = PKSigningUtil.
                    loadSigningInformationFromPKCS12FileAndIntermediateCertificateFile(
                            privateKeyPath, privateKeyPassword, appleWWDRCA);

            PKInMemorySigningUtil pKInMemorySigningUtil = new PKInMemorySigningUtil()
//            PKSigningInformation pkSigningInformation = new PKSigningInformation()
//                    loadSigningInformationFromPKCS12FileAndIntermediateCertificateFile(
//                            privateKeyPath, privateKeyPassword, appleWWDRCA);

            PKPass pass = new PKPass();
            pass.setPassTypeIdentifier("pass.com.ttndevelopment.dotripsuat");
            pass.setAuthenticationToken("dummyauthenticationtoken");
            pass.setSerialNumber("1111111111111111");
            pass.setTeamIdentifier("DoTrips LLC"); // replace this with your team ID
            pass.setOrganizationName("DoTrips LLC");
            pass.setDescription("Do trips Booking Confirmed");
            pass.setLogoText("Logo text ");

            PKBarcode barcode = new PKBarcode();
            barcode.setFormat(PKBarcodeFormat.PKBarcodeFormatPDF417);
            barcode.setMessageEncoding(Charset.forName("iso-8859-1"));
            barcode.setMessage("This is a pass to test");
            pass.setBarcode(barcode);

            PKGenericPass generic = new PKGenericPass();
            List<PKField> primaryFields = new ArrayList<PKField>();
            PKField member = new PKField();
            member.setKey("mykey"); // some unique key for primary field
            member.setValue("myvalue"); // some value
            primaryFields.add(member);
            generic.setPrimaryFields(primaryFields);
            pass.setGeneric(generic);

            PKLocation location = new PKLocation();
            location.setLatitude(37.33182); // replace with some lat
            location.setLongitude(-122.03118); // replace with some long
            List<PKLocation> locations = new ArrayList<PKLocation>();
            locations.add(location);
            pass.setLocations(locations);

            if (pass.isValid()) {
                String pathToTemplateDirectory = "/home/nitin/Downloads/Wallet/jpasskit-master/jpasskit/src/test/resources/pass_resources";
                // replace with your folder with the icons
                byte[] passZipAsByteArray = PKSigningUtil.
                        createSignedAndZippedPkPassArchive(pass, pathToTemplateDirectory, pkSigningInformation);
//                PKPassTemplateFolder passTemplateFolder = new PKPassTemplateFolder(pathToTemplateDirectory)
//                byte[] passZipAsByteArray = pKInMemorySigningUtil.
//                        createSignedAndZippedPkPassArchive(pass, passTemplateFolder, pkSigningInformation);

                String outputFile = "/home/nitin/Downloads/WalletPasses/dotrips.pkpass"; // change the name of the pass
                ByteArrayInputStream inputStream = new ByteArrayInputStream(passZipAsByteArray);
                IOUtils.copy(inputStream, new FileOutputStream(outputFile));
                render("Done!");
            } else {
                render("the pass is NOT Valid man!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            render("failed!");
        }
    }


    def test2() {
        String appleWWDRCA = "/home/nitin/Downloads/Certificates/AppleWWDRCA.pem"; // this is apple's developer relation cert
//        String privateKeyPath = "/home/nitin/Downloads/Certificates.p12"; // the private key you exported from keychain
        String privateKeyPath = "/home/nitin/Downloads/Certificates/Certificates.p12";
        // the private key you exported from keychain
        String privateKeyPassword = "igdefault"; // the password you used to export
//        String privateKeyPassword = "igdefault"; // the password you used to export
        String template_path = "/home/nitin/Downloads/Wallet/jpasskit-master/jpasskit/src/test/resources/pass_resources";

        PKSigningInformation pkSigningInformation = new PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(privateKeyPath, privateKeyPassword, appleWWDRCA);
        PKPassTemplateFolder passTemplate = new PKPassTemplateFolder(template_path);
        PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil();
        try {
            PKPass pass = new PKPass();
            pass.setPassTypeIdentifier("pass.com.ttndevelopment.dotripsuat");
            pass.setAuthenticationToken("dummyauthenticationtoken");
            pass.setSerialNumber("123123456");
            pass.setTeamIdentifier("KZS49YQT99"); // replace this with your team ID
            pass.setOrganizationName("DoTrips LLC");
            pass.setDescription("Do trips Booking Confirmed");
            pass.setLogoText("Logo text");
            pass.setWebServiceURL(new URL("https://example.tld/pass"))
            pass.setRelevantDate(new Date() + 2)

            PKGenericPass generic = new PKGenericPass();
            List<PKField> primaryFields = new ArrayList<PKField>();
            PKField member = new PKField();
            member.setKey("mykey"); // some unique key for primary field
            member.setValue("myvalue"); // some value
            primaryFields.add(member);
            generic.setPrimaryFields(primaryFields);
            pass.setGeneric(generic);

            PKLocation location = new PKLocation();
            location.setLatitude(37.33182); // replace with some lat
            location.setLongitude(-122.03118); // replace with some long
            List<PKLocation> locations = new ArrayList<PKLocation>();
            locations.add(location);
            pass.setLocations(locations);

            if (pass.isValid()) {
                byte[] passZipAsByteArray = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);
                String outputFile = "/home/nitin/Downloads/WalletPasses/ios.pkpass"; // change the name of the pass
                ByteArrayInputStream inputStream = new ByteArrayInputStream(passZipAsByteArray);
                IOUtils.copy(inputStream, new FileOutputStream(outputFile));
                render("Done!");
            } else {
                render("the pass is NOT Valid man!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            render("failed!");
        }
    }

    def working(){
        println "Called>>>>>>>>>>>>>>>>>>>>>>>"
        String appleWWDRCA = "/home/nitin/Downloads/Certificates/AppleWWDRCA.pem"; // this is apple's developer relation cert
        String privateKeyPath = "/home/nitin/Downloads/Certificates/Certificates.p12";
        String privateKeyPassword = "igdefault"; // the password you used to export
        String template_path = "/home/nitin/Downloads/Wallet/jpasskit-master/jpasskit/src/test/resources/pass_resources";

        PKSigningInformation pkSigningInformation = new PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(privateKeyPath, privateKeyPassword, appleWWDRCA);
        PKPassTemplateFolder passTemplate = new PKPassTemplateFolder(template_path);
        PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil();
        try {
            PKPass pass = new PKPass();
            pass.setPassTypeIdentifier("pass.com.ttndevelopment.dotripsuat");
            pass.setAuthenticationToken("dummyauthenticationtoken");
            pass.setSerialNumber("123123456");
            pass.setTeamIdentifier("KZS49YQT99"); // replace this with your team ID
            pass.setOrganizationName("DoTrips LLC");
            pass.setDescription("Do trips Booking Confirmed");
            pass.setLogoText("Logo text");
            pass.setWebServiceURL(new URL("https://example.tld/pass"))
            pass.setRelevantDate(new Date() + 2)

            PKGenericPass generic = new PKGenericPass();
            List<PKField> primaryFields = new ArrayList<PKField>();
            PKField member = new PKField();
            member.setKey("mykey"); // some unique key for primary field
            member.setValue("myvalue"); // some value
            primaryFields.add(member);
            generic.setPrimaryFields(primaryFields);
            pass.setGeneric(generic);

            PKLocation location = new PKLocation();
            location.setLatitude(37.33182); // replace with some lat
            location.setLongitude(-122.03118); // replace with some long
            List<PKLocation> locations = new ArrayList<PKLocation>();
            locations.add(location);
            pass.setLocations(locations);

            if (pass.isValid()) {
                byte[] passZipAsByteArray = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);
                String outputFile = "/home/nitin/Downloads/WalletPasses/ios.pkpass"; // change the name of the pass
                ByteArrayInputStream inputStream = new ByteArrayInputStream(passZipAsByteArray);
                IOUtils.copy(inputStream, new FileOutputStream(outputFile));
                render ("Done!");
            } else {
                render ("the pass is NOT Valid man!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            render ("failed!");
        }
    }*/
}
