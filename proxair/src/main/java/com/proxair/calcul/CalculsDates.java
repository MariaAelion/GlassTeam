package com.proxair.calcul;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculsDates {
	
	public static LocalDate paques(int year) {
		if (year < 1583) {
			throw new IllegalStateException();
		}
		int n = year % 19;
		int c = year / 100;
		int u = year % 100;
		int s = c / 4;
		int t = c % 4;
		int p = (c + 8) / 25;
		int q = (c - p + 1) / 3;
		int e = (19 * n + c - s - q + 15) % 30;
		int b = u / 4;
		int d = u % 4;
		int L = (32 + 2 * t + 2 * b - e - d) % 7;
		int h = (n + 11 * e + 22 * L) / 451;
		int m = (e + L - 7 * h + 114) / 31;
		int j = (e + L - 7 * h + 114) % 31;
 
		return LocalDate.of(year, m, j + 1);
	}
	
	
	public static boolean ferie(LocalDate date) {
		final int day = date.getDayOfMonth();
		switch (date.getMonth()) {
		case JANUARY:
			if (day == 1 || day == 6) {
				// Jour de l'an et Epiphanie
				return true;
			}
			break;
		case FEBRUARY:
			if (day == 14) {
				// St Valentin
				return true;
			}
			break;
		case MAY:
			if (day == 1 || day == 8) {
				// Fête du travail et Victoire 1945
				return true;
			}
			break;
		case JULY:
			if (day == 14) {
				// Fête Nationale
				return true;
			}
			break;
		case AUGUST:
			if (day == 15) {
				// Assomption
				return true;
			}
			break;
		case NOVEMBER:
			if (day == 1 || day == 11) {
				// Toussaint et Armistice 1918
				return true;
			}
			break;
		case DECEMBER:
			if (day == 25 || day == 31) {
				// Noël et Saint-sylvestre
				return true;
			}
			break;
		default:
		}
 
		if (date.getMonthValue() < 7) {
			// Avant juillet on doit aussi vérifier les fêtes liées à Paques
			LocalDate paques = paques(date.getYear());
			int days = (int) ChronoUnit.DAYS.between(paques, date);
			switch (days) {
			case -47: // mardi gras : 47 jours avant Pâques
			case 0: // Paques
			case 1: // lundi de Pâques : 1 jour après Pâques
			case 39: // Ascension : 39 jours après Pâques
			case 50: // Pentecôte : 50 jours après Pâques
			case 51: // L. de Pentecôte : 51 jours après Paques
				return true;
			}
		}
		return false;
	}
	
	public static boolean weekEnd(LocalDate date) {
		
		switch (date.getDayOfWeek() ) {
		case SATURDAY: return true;
		case SUNDAY: return true;
		default: return false;
		}

	}
}