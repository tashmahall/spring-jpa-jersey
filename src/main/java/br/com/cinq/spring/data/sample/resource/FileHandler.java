package br.com.cinq.spring.data.sample.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.cinq.spring.data.sample.resource.exceptions.InvalidLineException;
public abstract class FileHandler<T>  {

	public void loadToDataTableCityTables(Scanner sc,String splitChar) throws InvalidLineException {
		int errorLineIdentifier=0;
		List<String> errorMesageList= new ArrayList<>();
		while (sc.hasNext()) {
			String[] array = sc.nextLine().split(splitChar);
			try {
				valid(array);
				T t = createEntity(array);
				save(t);
			} catch (InvalidLineException e) {
				errorLineIdentifier++;
				errorMesageList.add("Line ["+errorLineIdentifier+"] - Error message ["+e.getMessage()+"]");
			}
		}
		if(errorLineIdentifier!=0) {
			throw new InvalidLineException(errorMesageList);
		}
	}
	public abstract void save(T t) throws InvalidLineException ;
	protected abstract T createEntity(String[] string) ;
	protected abstract void valid(String[] stringCity) throws InvalidLineException;
}
