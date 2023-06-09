package michaelstudy.ecommercenewapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversorDeDatas {

	public static LocalDate converteToLocalDate(String data) {

		return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	public static String converteLocalDateParaString(LocalDate data) {

		return data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}

}