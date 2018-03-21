package br.com.cinq.spring.data.sample.resource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;

@Controller
public class HomeController {
	@Autowired
	private CityFileHandler cityFH;
	@Autowired
	private CountryFileHandler countryFH;
	
	@GetMapping("/index")
	public String index() {
		return "/upload";
	}
	@PostMapping(value="/uploadCity")
	 public String fileUploadCity(@RequestParam("file") MultipartFile file, @RequestParam("splitChar")String splitChar, RedirectAttributes redirectAttributes) {
		return fileUpload(file, splitChar, redirectAttributes, cityFH);
    }
	@PostMapping(value="/uploadCountry")
	 public String fileUploadCountry(@RequestParam("fileCountry") MultipartFile file, @RequestParam("splitCharCountry")String splitChar, RedirectAttributes redirectAttributes ) {
		return fileUpload(file, splitChar, redirectAttributes, countryFH);
   }
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
	private String fileUpload(MultipartFile file, String splitChar, RedirectAttributes redirectAttributes, FileHandler<?> fh) {
		 List<String> lTemp = new ArrayList<>();
		if (file.isEmpty()) {
        	lTemp.add("Please select a file to upload");
            redirectAttributes.addFlashAttribute("messages",lTemp );
            return "redirect:uploadStatus";
        }
		if (splitChar == null|| splitChar.isEmpty()) {
        	lTemp.add("The Field Char Separator cannot be null");
            redirectAttributes.addFlashAttribute("messages",lTemp );
            return "redirect:uploadStatus";
        }
        Reader targetReader = null;
        try {
            byte[] bytes = file.getBytes();
            targetReader = new StringReader(new String(bytes));
            Scanner sc = new Scanner(targetReader);
            fh.loadToDataTableCityTables(sc, splitChar);
            lTemp.add("You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("messages",lTemp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidLineException e) {
        	 lTemp.addAll(e.getListErrorMessages());
        	redirectAttributes.addFlashAttribute("messages", lTemp);
		}finally {
			try {
				targetReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return "redirect:uploadStatus";
	}
}
