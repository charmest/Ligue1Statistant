package com.statistant.ligue1.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.statistant.ligue1.controller.InvalidNumberToConvertFromBooleanException;
import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.TeamController;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullRivalException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.view.InitializeWindow;

/**
 * Basic operations.
 * 
 * @author Thomas CHARMES
 */
public class Ligue1Utils {

	public final static String FORM_FIELD_SURNOM_EQUIPE_DOMICILE = "SurnomE1";
	public final static String FORM_FIELD_SURNOM_EQUIPE_EXTERIEUR = "SurnomE2";
	public final static String FORM_FIELD_CHECK_BOX_VICTOIRE_EQUIPE_DOMICILE = "VictoireEquipeDomicile";
	public final static String FORM_FIELD_CHECK_BOX_VICTOIRE_EQUIPE_EXTERIEUR = "VictoireEquipeExterieur";
	public final static String FORM_FIELD_CHECK_BOX_NUL = "nul";
	public final static String FORM_FIELD_CHECK_BOX_IMPORTANT_EQUIPE_DOMICILE = "ImportantEquipeDomicile";
	public final static String FORM_FIELD_CHECK_BOX_IMPORTANT_EQUIPE_EXTERIEUR = "ImportantEquipeExterieur";
	public final static String FORM_FIELD_CHECK_BOX_EQUIPE_DOMICILE_MIEUX_CLASSEE = "EquipeDomicileMieuxClassee";
	public final static String FORM_FIELD_CHECK_BOX_REINITIALISER_SAISON = "ResetSeason";
	public final static String FORM_FIELD_CHECK_BOX_RESET_ALL_SEASON = "ResetAllSeason";
	public final static String FORM_FIELD_CHECK_BOX_COMPTABILISE = "Comptabilise";
	public final static String FORM_FIELD_SCORE = "Score";
	public final static String FORM_BUTTON_GENERER_RAPPORT_STATISTIQUES = "Statistiques";
	public final static String FORM_FIELD_CHECK_BOX_COMPTABILISE_RAPPORT_STATISTIQUES_JOURNEE = "RapportStatistiques";
	public final static String FORM_FIELD_JOURNEY = "Journey";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES = "nombreMatchsJoues";
	public final static String TABLE_FIELD_STRING_CLASSEMENT = "classement";
	public final static String TABLE_FIELD_DIFFERENCE_DE_BUTS = "GoalAverage";
	public final static String TABLE_FIELD_STRING_CLASSEMENT_DOMICILE = "classementDomicile";
	public final static String TABLE_FIELD_DIFFERENCE_DE_BUTS_DOMICILE = "HomeGoalAverage";
	public final static String TABLE_FIELD_STRING_CLASSEMENT_EXTERIEUR = "classementExterieur";
	public final static String TABLE_FIELD_DIFFERENCE_DE_BUTS_EXTERIEUR = "AwayGoalAverage";
	public final static String TABLE_FIELD_STRING_ID = "id";
	public final static String TABLE_FIELD_NAME = "nom";
	public final static String TABLE_FIELD_SURNAME = "surnom";
	public final static String TABLE_FIELD_NOMBRE_POINTS = "NombrePoints";
	public final static String TABLE_FIELD_NOMBRE_POINTS_DOMICILE = "NombrePointsDomicile";
	public final static String TABLE_FIELD_NOMBRE_POINTS_EXTERIEUR = "NombrePointsExterieur";

	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_5 = "matchPrecedentCinq";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_5_DOMICILE = "MatchPrecedentCinqDomicile";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_5_EXTERIEUR = "MatchPrecedentCinqExterieur";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_4 = "matchPrecedentQuatre";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_4_DOMICILE = "MatchPrecedentQuatreDomicile";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_4_EXTERIEUR = "MatchPrecedentQuatreExterieur";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_3 = "matchPrecedentTrois";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_3_DOMICILE = "MatchPrecedentTroisDomicile";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_3_EXTERIEUR = "MatchPrecedentTroisExterieur";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_2 = "matchPrecedentDeux";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_2_DOMICILE = "MatchPrecedentDeuxDomicile";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_2_EXTERIEUR = "MatchPrecedentDeuxExterieur";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_1 = "matchPrecedentUn";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_1_DOMICILE = "MatchPrecedentUnDomicile";
	public final static String TABLE_FIELD_STRING_MATCH_PRECEDENT_1_EXTERIEUR = "MatchPrecedentUnExterieur";

	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_INFERIEUR = "NombreDClassementInf";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_INFERIEUR_DOMICILE = "NombreDClassementInfDomicile";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_INFERIEUR_EXTERIEUR = "NombreDClassementInfExterieur";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_SUPERIEUR = "NombreDClassementSup";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_SUPERIEUR_DOMICILE = "NombreDClassementSupDomicile";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_CLASSEMENT_SUPERIEUR_EXTERIEUR = "NombreDClassementSupExterieur";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_IMPORTANT_DOMICILE = "NombreDImportantsDomicile";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_IMPORTANT_EXTERIEUR = "NombreDImportantsExterieur";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_BANAL = "NombreDMatchBanal";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_BANAL_DOMICILE = "NombreDMatchBanalDomicile";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_BANAL_EXTERIEUR = "NombreDMatchBanalExterieur";
	public final static String TABLE_FIELD_NOMBRE_DEFAITE_CONTRE_IMPORTANT = "NombreDMatchsImportants";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_DOMICILE = "nombreMatchsJouesDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_EXTERIEUR = "nombreMatchsJouesExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_GAGNES = "nombreMatchsGagnes";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_GAGNES_DOMICILE = "nombreMatchsGagnesDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_GAGNES_EXTERIEUR = "nombreMatchsGagnesExterieur";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_BANAL = "NombreMatchsJouesBanal";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_BANAL_DOMICILE = "NombreMatchsJouesBanalDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_BANAL_EXTERIEUR = "NombreMatchsJouesBanalExterieur";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_INF = "NombreMatchsJouesClassementInf";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_INF_DOMICILE = "NombreMatchsJouesClassementInfDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_INF_EXTERIEUR = "NombreMatchsJouesClassementInfExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_SUP = "NombreMatchsJouesClassementSup";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_SUP_EXTERIEUR = "NombreMatchsJouesClassementSupExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_CLASSEMENT_SUP_DOMICILE = "NombreMatchsJouesClassementSupDomicile";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_IMPORTANTS = "NombreMatchsJouesImportants";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_IMPORTANTS_DOMICILE = "NombreMatchsJouesImportantsDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_JOUES_IMPORTANTS_EXTERIEUR = "NombreMatchsJouesImportantsExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS = "nombreMatchsNuls";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_DOMICILE = "nombreMatchsNulsDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_EXTERIEUR = "nombreMatchsNulsExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_PERDUS = "nombreMatchsPerdus";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_PERDUS_DOMICILE = "nombreMatchsPerdusDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_PERDUS_EXTERIEUR = "nombreMatchsPerdusExterieur";

	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_INF = "NombreNClassementInf";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_INF_DOMICILE = "NombreNClassementInfDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_INF_EXTERIEUR = "NombreNClassementInfExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_SUP = "NombreNClassementSup";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_SUP_DOMICILE = "NombreNClassementSupDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_CLASSEMENT_SUP_EXTERIEUR = "NombreNClassementSupExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_IMPORTANT_DOMICILE = "NombreNImportantsDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_IMPORTANT_EXTERIEUR = "NombreNImportantsExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_BANAL = "NombreNMatchBanal";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_BANAL_DOMICILE = "NombreNMatchBanalDomicile";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_BANAL_EXTERIEUR = "NombreNMatchBanalExterieur";
	public final static String TABLE_FIELD_NOMBRE_MATCHS_NULS_IMPORTANT = "NombreNMatchsImportants";

	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_INF = "NombreVClassementInf";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_INF_DOMICILE = "NombreVClassementInfDomicile";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_INF_EXTERIEUR = "NombreVClassementInfExterieur";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_SUP = "NombreVClassementSup";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_SUP_DOMICILE = "NombreVClassementSupDomicile";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_CLASSEMENT_SUP_EXTERIEUR = "NombreVClassementSupExterieur";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_IMPORTANT_DOMICILE = "NombreVImportantsDomicile";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_IMPORTANT_EXTERIEUR = "NombreVImportantsExterieur";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_BANAL = "NombreVMatchBanal";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_BANAL_DOMICILE = "NombreVMatchBanalDomicile";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_BANAL_EXTERIEUR = "NombreVMatchBanalExterieur";
	public final static String TABLE_FIELD_NOMBRE_VICTOIRES_IMPORTANT = "NombreVMatchsImportants";

	public final static String TABLE_FIELD_SERIE_D_EN_COURS = "SerieDEnCours";
	public final static String TABLE_FIELD_SERIE_D_EN_COURS_DOMICILE = "SerieDEnCoursDomicile";
	public final static String TABLE_FIELD_SERIE_D_EN_COURS_EXTERIEUR = "SerieDEnCoursExterieur";
	public final static String TABLE_FIELD_SERIE_N_EN_COURS = "SerieNEnCours";
	public final static String TABLE_FIELD_SERIE_N_EN_COURS_DOMICILE = "SerieNEnCoursDomicile";
	public final static String TABLE_FIELD_SERIE_N_EN_COURS_EXTERIEUR = "SerieNEnCoursExterieur";
	public final static String TABLE_FIELD_SERIE_V_EN_COURS = "SerieVEnCours";
	public final static String TABLE_FIELD_SERIE_V_EN_COURS_DOMICILE = "SerieVEnCoursDomicile";
	public final static String TABLE_FIELD_SERIE_V_EN_COURS_EXTERIEUR = "SerieVEnCoursExterieur";

	public static final String TABLE_CONFRONTATIONS_FIELD_RECENT_5 = "Recent5";
	public static final String TABLE_CONFRONTATIONS_FIELD_RECENT_4 = "Recent4";
	public static final String TABLE_CONFRONTATIONS_FIELD_RECENT_3 = "Recent3";
	public static final String TABLE_CONFRONTATIONS_FIELD_RECENT_2 = "Recent2";
	public static final String TABLE_CONFRONTATIONS_FIELD_RECENT_1 = "Recent1";
	public static final String TABLE_CONFRONTATIONS_FIELD_MATCH = "match";
	public static final String TABLE_CONFRONTATIONS_FIELD_DATE_LAST_MODIFICATION = "lastModificationDate";

	public static final String TABLE_STATISTIQUES_FIELD_MATCH = "match";
	public static final String TABLE_STATISTIQUES_FIELD_MOYENNE_BUTS_DOMICILE = "MoyButsEquipeDom";
	public static final String TABLE_STATISTIQUES_FIELD_MOYENNE_BUTS_EXTERIEUR = "MoyButsEquipeExt";
	public static final String TABLE_STATISTIQUES_FIELD_MOYENNE_BUTS_MATCH = "MoyButsMatch";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_NULS = "Nul";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_LDEM = "LDEM";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_VICTOIRE_DOMICILE = "VEquipeDom";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_VICTOIRE_EXTERIEUR = "VEquipeExt";

	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_PLUS_0_5_BUTS = "Plus05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_PLUS_1_5_BUTS = "Plus15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_PLUS_2_5_BUTS = "Plus25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_PLUS_3_5_BUTS = "Plus35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_PLUS_4_5_BUTS = "Plus45";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_MOINS_0_5_BUTS = "Moins05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_MOINS_1_5_BUTS = "Moins15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_MOINS_2_5_BUTS = "Moins25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_MOINS_3_5_BUTS = "Moins35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_MATCH_MOINS_4_5_BUTS = "Moins45";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_PLUS_0_5_BUTS = "E1Plus05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_PLUS_1_5_BUTS = "E1Plus15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_PLUS_2_5_BUTS = "E1Plus25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_PLUS_3_5_BUTS = "E1Plus35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_PLUS_4_5_BUTS = "E1Plus45";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_MOINS_0_5_BUTS = "E1Moins05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_MOINS_1_5_BUTS = "E1Moins15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_MOINS_2_5_BUTS = "E1Moins25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_MOINS_3_5_BUTS = "E1Moins35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_DOMICILE_MOINS_4_5_BUTS = "E1Moins45";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_PLUS_0_5_BUTS = "E2Plus05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_PLUS_1_5_BUTS = "E2Plus15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_PLUS_2_5_BUTS = "E2Plus25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_PLUS_3_5_BUTS = "E2Plus35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_PLUS_4_5_BUTS = "E2Plus45";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_MOINS_0_5_BUTS = "E2Moins05";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_MOINS_1_5_BUTS = "E2Moins15";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_MOINS_2_5_BUTS = "E2Moins25";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_MOINS_3_5_BUTS = "E2Moins35";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EXTERIEUR_MOINS_4_5_BUTS = "E2Moins45";

	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_1 = "EcartButMoins1";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_2 = "EcartButMoins2";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_3 = "EcartButMoins3";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_4 = "EcartButMoins4";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_5 = "EcartButMoins5";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_6 = "EcartButMoins6";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_7 = "EcartButMoins7";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_8 = "EcartButMoins8";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_MOINS_9 = "EcartButMoins9";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_1 = "EcartButPlus1";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_2 = "EcartButPlus2";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_3 = "EcartButPlus3";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_4 = "EcartButPlus4";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_5 = "EcartButPlus5";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_6 = "EcartButPlus6";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_7 = "EcartButPlus7";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_8 = "EcartButPlus8";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_ECART_BUT_PLUS_9 = "EcartButPlus9";

	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_0_BUT_MARQUE = "E1Exactement0ButMarque";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_1_BUT_MARQUE = "E1Exactement1ButMarque";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_2_BUTS_MARQUES = "E1Exactement2ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_3_BUTS_MARQUES = "E1Exactement3ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_4_BUTS_MARQUES = "E1Exactement4ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_5_BUTS_MARQUES = "E1Exactement5ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_0_BUT_MARQUE = "E2Exactement0ButMarque";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_1_BUT_MARQUE = "E2Exactement1ButMarque";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_2_BUTS_MARQUES = "E2Exactement2ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_3_BUTS_MARQUES = "E2Exactement3ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_4_BUTS_MARQUES = "E2Exactement4ButsMarques";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_5_BUTS_MARQUES = "E2Exactement5ButsMarques";

	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_0_BUT_ENCAISSE = "E1Exactement0ButEncaisse";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_1_BUT_ENCAISSE = "E1Exactement1ButEncaisse";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_2_BUTS_ENCAISSES = "E1Exactement2ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_3_BUTS_ENCAISSES = "E1Exactement3ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_4_BUTS_ENCAISSES = "E1Exactement4ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_DOMICILE_5_BUTS_ENCAISSES = "E1Exactement5ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_0_BUT_ENCAISSE = "E2Exactement0ButEncaisse";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_1_BUT_ENCAISSE = "E2Exactement1ButEncaisse";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_2_BUTS_ENCAISSES = "E2Exactement2ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_3_BUTS_ENCAISSES = "E2Exactement3ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_4_BUTS_ENCAISSES = "E2Exactement4ButsEncaisses";
	public static final String TABLE_STATISTIQUES_FIELD_POURCENTAGE_EQUIPE_EXTERIEUR_5_BUTS_ENCAISSES = "E2Exactement5ButsEncaisses";

	public final static String ORGANIZATION_NAME = "DefaultOrganization";
	public final static String APPLICATION_NAME = "ApplicationLigue1";
	public final static String DATA_RESERVOIR_EQUIPES_NAME = "ReservoirDeDonneesEquipes";
	public final static String TABLE_EQUIPES_NAME = "tableEquipes";
	public final static String TABLE_STATISTIQUES_NAME = "StatistiquesMatchs";
	public final static String TABLE_CONFRONTATIONS_NAME = "tableConfrontations";
	public final static String GROUP_PROCESS_NAME = "GroupeDeProcessusLigue1";
	public final static String PROCESS_NAME = "match";
	public final static String VERSION_PROCESS_NAME = "match_1.0";

	public static boolean isScoreWellFormed(String score) {
		String regex = "^[0-9]-[0-9]$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(score);
		return m.matches();
	}

	public static void resetAllSeason() {
		Collection<Team> allTeams = DatabaseConnection.getAllTeams();
		for (Team team : allTeams) {
			team.setNbMatchesPlayed(0);
			team.setNbPoints(0);
			team.setNbHomePoints(0);
			team.setNbAwayPoints(0);
			team.setRecent5("O");
			team.setRecent4("O");
			team.setRecent3("O");
			team.setRecent2("O");
			team.setRecent1("O");
			team.setHomeRecent5("O");
			team.setHomeRecent4("O");
			team.setHomeRecent3("O");
			team.setHomeRecent2("O");
			team.setHomeRecent1("O");
			team.setAwayRecent5("O");
			team.setAwayRecent4("O");
			team.setAwayRecent3("O");
			team.setAwayRecent2("O");
			team.setAwayRecent1("O");
			team.setNbLossesAgainstStandingInferior(0);
			team.setNbLossesAgainstStandingInferiorAtHome(0);
			team.setNbLossesAgainstStandingInferiorAway(0);
			team.setNbLossesAgainstStandingSuperior(0);
			team.setNbLossesAgainstStandingSuperiorAtHome(0);
			team.setNbLossesAgainstStandingSuperiorAway(0);
			team.setNbLossesAgainstImportantOpponentAtHome(0);
			team.setNbLossesAgainstImportantOpponentAway(0);
			team.setNbLossesAgainstNormalOpponent(0);
			team.setNbLossesAgainstNormalOpponentAtHome(0);
			team.setNbLossesAgainstImportantOpponentAway(0);
			team.setNbLossesAgainstImportantOpponent(0);
			team.setNbHomeMatchesPlayed(0);
			team.setNbAwayMatchesPlayed(0);
			team.setNbWins(0);
			team.setNbHomeWins(0);
			team.setNbAwayWins(0);
			team.setGoalAverage(0);
			team.setHomeGoalAverage(0);
			team.setAwayGoalAverage(0);
			team.setStanding(1);
			team.setHomeStanding(1);
			team.setAwayStanding(1);
			team.setNbMatchesPlayedAgainstNormalOpponent(0);
			team.setNbMatchesPlayedAgainstNormalOpponentAtHome(0);
			team.setNbMatchesPlayedAgainstNormalOpponentAway(0);
			team.setNbMatchesPlayedAgainstStandingInferior(0);
			team.setNbMatchesPlayedAgainstStandingInferiorAtHome(0);
			team.setNbMatchesPlayedAgainstStandingInferiorAway(0);
			team.setNbMatchesPlayedAgainstStandingSuperior(0);
			team.setNbMatchesPlayedAgainstStandingSuperiorAtHome(0);
			team.setNbMatchesPlayedAgainstStandingSuperiorAway(0);
			team.setNbMatchesPlayedAgainstImportantOpponent(0);
			team.setNbMatchesPlayedAgainstImportantOpponentAtHome(0);
			team.setNbMatchesPlayedAgainstImportantOpponentAway(0);
			team.setNbDraws(0);
			team.setNbHomeDraws(0);
			team.setNbAwayDraws(0);
			team.setNbLosses(0);
			team.setNbHomeLosses(0);
			team.setNbAwayLosses(0);
			team.setNbDrawsAgainstStandingInferior(0);
			team.setNbDrawsAgainstStandingInferiorAtHome(0);
			team.setNbDrawsAgainstStandingInferiorAway(0);
			team.setNbDrawsAgainstStandingSuperior(0);
			team.setNbDrawsAgainstStandingSuperiorAtHome(0);
			team.setNbDrawsAgainstStandingSuperiorAway(0);
			team.setNbDrawsAgainstImportantOpponentAtHome(0);
			team.setNbDrawsAgainstImportantOpponentAway(0);
			team.setNbDrawsAgainstNormalOpponent(0);
			team.setNbDrawsAgainstNormalOpponentAtHome(0);
			team.setNbDrawsAgainstNormalOpponentAway(0);
			team.setNbDrawsAgainstImportantOpponent(0);
			team.setNbWinsAgainstStandingInferior(0);
			team.setNbWinsAgainstStandingInferiorAtHome(0);
			team.setNbWinsAgainstStandingInferiorAway(0);
			team.setNbWinsAgainstStandingSuperior(0);
			team.setNbWinsAgainstStandingSuperiorAtHome(0);
			team.setNbWinsAgainstStandingSuperiorAway(0);
			team.setNbWinsAgainstImportantOpponent(0);
			team.setNbWinsAgainstImportantOpponentAtHome(0);
			team.setNbWinsAgainstImportantOpponentAway(0);
			team.setNbWinsAgainstNormalOpponent(0);
			team.setNbWinsAgainstNormalOpponentAtHome(0);
			team.setNbWinsAgainstNormalOpponentAway(0);
			team.setLoosingSerie(0);
			team.setDrawSerie(0);
			team.setWinningSerie(0);
			team.setHomeLoosingSerie(0);
			team.setHomeDrawSerie(0);
			team.setHomeWinningSerie(0);
			team.setAwayLoosingSerie(0);
			team.setAwayDrawSerie(0);
			team.setAwayWinningSerie(0);
			DatabaseConnection.createOrUpdateTeam(team);
			Ligue1Utils.reportInfo(
					"Les informations de l'équipe " + team.getName() + " ont été réinitialisées dans la table Teams.");
		}
		DatabaseConnection.deleteAllMatches();
		Ligue1Utils.reportInfo("Tous les matchs ont été supprimés avec succès.");
		Ligue1Utils.reportInfo("Les informations de toutes les équipes ont été réinitialisées dans la table Teams.");
		InitializeWindow.alertInfo(
				"La saison a été réinitialisée avec succès. Les matchs ont été supprimés et les données des équipes ont été remises à zéro.");
	}

	public static boolean setResult(Team team1, Team team2, boolean e1mieuxClassee, boolean importantE1,
			boolean importantE2, boolean victoireE1, boolean matchNul, boolean victoireE2, String score) {

		setGoalDifferenceIn3Standings(team1, team2, score);
		// Les deux équipes saisies évoluent en Ligue 1
		// Elles jouent toutes les deux un match
		setNombreMatchsJouesPlusUn(team1);
		setNombreMatchsJouesPlusUn(team2);
		// E1 joue à domicile
		// E2 joue à l'extérieur
		setNombreMatchsJouesDomicilePlusUn(team1);
		setNombreMatchsJouesExterieurPlusUn(team2);
		// si E1 est mieux classée :
		// E1 joue contre equipe moins bien classée
		// E2 joue contre equipe mieux classée
		if (e1mieuxClassee) {
			setNombreMatchsJouesContreEquipeMieuxClasseePlusUn(team2);
			setNombreMatchsJouesContreEquipeMoinsBienClasseePlusUn(team1);
			setNombreMatchsJouesContreEquipeMieuxClasseeExterieurPlusUn(team2);
			setNombreMatchsJouesContreEquipeMoinsBienClasseeDomicilePlusUn(team1);
		}
		// Si E2 est mieux classée :
		// E1 joue contre une équipe mieux classée
		// E2 joue contre une équipe moins bien classée
		else {
			setNombreMatchsJouesContreEquipeMieuxClasseePlusUn(team1);
			setNombreMatchsJouesContreEquipeMoinsBienClasseePlusUn(team2);
			setNombreMatchsJouesContreEquipeMieuxClasseeDomicilePlusUn(team1);
			setNombreMatchsJouesContreEquipeMoinsBienClasseeExterieurPlusUn(team2);
		}
		// Si c'est un match important pour E1 :
		// E1 joue un match important
		// E1 joue un match important à domicile
		if (importantE1) {
			setNombreMatchsJouesImportantsPlusUn(team1);
			setNombreMatchsJouesImportantsDomicilePlusUn(team1);
		}
		// Si c'est un match banal pour E1 :
		// E1 joue un match banal
		// E1 joue un match banal à domicile
		else {
			setNombreMatchsJouesBanalPlusUn(team1);
			setNombreMatchsJouesBanalDomicilePlusUn(team1);
		}
		// Si c'est un match important pour E2 :
		// E2 joue un match important
		// E2 joue un match important à l'extérieur
		if (importantE2) {
			setNombreMatchsJouesImportantsPlusUn(team2);
			setNombreMatchsJouesImportantsExterieurPlusUn(team2);
		}
		// Si c'est un match banal pour E2 :
		// E2 joue un match banal
		// E2 joue un match banal à l'extérieur
		else {
			setNombreMatchsJouesBanalPlusUn(team2);
			setNombreMatchsJouesBanalExterieurPlusUn(team2);
		}
		// Si E1 gagne :
		// ajout 3 points aux classements domicile et général
		// gérer les séries des deux équipes (Dom, Ext, Gen)
		// E1 nombre de matchs gagnés +1 Dom et Gen
		// E2 nombre de matchs perdus +1 Ext et Gen
		if (victoireE1) {
			add3points(team1);
			add3pointsDomicile(team1);
			// gère les VNDVV, effectue un roulement
			addSerieEnCours(team1, "V");
			addSerieEnCours(team2, "D");
			addSerieEnCoursDomicile(team1, "V");
			addSerieEnCoursExterieur(team2, "D");
			// gere les totaux V:3 N:1 D:1
			setNombreMatchsGagnesPlusUn(team1);
			setNombreMatchsGagnesDomicilePlusUn(team1);
			setNombreMatchsPerdusPlusUn(team2);
			setNombreMatchsPerdusExterieurPlusUn(team2);
			// Si E1 gagne et est mieux classée :
			// E1 nombre matchs gagnés contre moins bien classée +1 gen et dom
			// E2 nombre matchs perdus contre mieux classée +1 gen et ext
			if (e1mieuxClassee) {
				setNombreMatchsGagnesContreEquipeMoinsBienClasseePlusUn(team1);
				setNombreMatchsPerdusContreEquipeMieuxClasseePlusUn(team2);
				setNombreMatchsGagnesContreEquipeMoinsBienClasseeDomicilePlusUn(team1);
				setNombreMatchsPerdusContreEquipeMieuxClasseeExterieurPlusUn(team2);
			}
			// Si E1 gagne et est moins bien classée :
			// E1 nombre matchs gagnés contre mieux classée +1 gen et dom
			// E2 nombre matchs perdus contre moins bien classée +1 gen et ext
			else {
				setNombreMatchsGagnesContreEquipeMieuxClasseePlusUn(team1);
				setNombreMatchsPerdusContreEquipeMoinsBienClasseePlusUn(team2);
				setNombreMatchsGagnesContreEquipeMieuxClasseeDomicilePlusUn(team1);
				setNombreMatchsPerdusContreEquipeMoinsBienClasseeExterieurPlusUn(team2);
			}
			// Si E1 gagne et le match est important pour lui :
			// E1 nombre de matchs gagnés importants +1 gen et dom
			if (importantE1) {
				setNombreMatchsGagnesImportantsPlusUn(team1);
				setNombreMatchsGagnesImportantsDomicilePlusUn(team1);
			}
			// Si E1 gagne et le match est banal pour lui:
			// E1 nombre de matchs gagnés banal +1 gen et dom
			else {
				setNombreMatchsGagnesBanalPlusUn(team1);
				setNombreMatchsGagnesBanalDomicilePlusUn(team1);
			}
			// Si E1 gagne et le match est important pour E2:
			// E2 nombre de matchs perdus importants +1 gen et ext
			if (importantE2) {
				setNombreMatchsPerdusImportantsPlusUn(team2);
				setNombreMatchsPerdusImportantsExterieurPlusUn(team2);
			}
			// Si E1 gagne et le match est banal pour E2 :
			// E2 nombre de matchs perdus banal +1 gen et ext
			else {
				setNombreMatchsPerdusBanalPlusUn(team2);
				setNombreMatchsPerdusBanalExterieurPlusUn(team2);
			}
		}
		// E1 n'a pas gagné
		else {
			if (victoireE2) {
				add3points(team2);
				add3pointsExterieur(team2);
				// gère les VNDVV
				addSerieEnCours(team1, "D");
				addSerieEnCours(team2, "V");
				addSerieEnCoursDomicile(team1, "D");
				addSerieEnCoursExterieur(team2, "V");
				// gere les V:3 N:1 D:1
				setNombreMatchsGagnesPlusUn(team2);
				setNombreMatchsGagnesExterieurPlusUn(team2);
				setNombreMatchsPerdusPlusUn(team1);
				setNombreMatchsPerdusDomicilePlusUn(team1);
				// Si E2 gagne et est mieux classée :
				// E2 nombre matchs gagnés contre moins bien classée +1 gen et ext
				// E1 nombre matchs perdus contre mieux classée +1 gen et dom
				if (!e1mieuxClassee) {
					setNombreMatchsGagnesContreEquipeMoinsBienClasseePlusUn(team2);
					setNombreMatchsPerdusContreEquipeMieuxClasseePlusUn(team1);
					setNombreMatchsGagnesContreEquipeMoinsBienClasseeExterieurPlusUn(team2);
					setNombreMatchsPerdusContreEquipeMieuxClasseeDomicilePlusUn(team1);
				}
				// Si E2 gagne et est moins bien classée :
				// E2 nombre matchs gagnés contre mieux classée +1 gen et ext
				// E1 nombre matchs perdus contre moins bien classée +1 gen et dom
				else {
					setNombreMatchsGagnesContreEquipeMieuxClasseePlusUn(team2);
					setNombreMatchsPerdusContreEquipeMoinsBienClasseePlusUn(team1);
					setNombreMatchsGagnesContreEquipeMieuxClasseeExterieurPlusUn(team2);
					setNombreMatchsPerdusContreEquipeMoinsBienClasseeDomicilePlusUn(team1);
				}
				// Si E2 gagne et le match est important pour lui :
				// E2 nombre de matchs gagnés importants +1 gen et ext
				if (importantE2) {
					setNombreMatchsGagnesImportantsPlusUn(team2);
					setNombreMatchsGagnesImportantsExterieurPlusUn(team2);
				}
				// Si E2 gagne et le match est banal pour lui:
				// E2 nombre de matchs gagnés banal +1 gen et ext
				else {
					setNombreMatchsGagnesBanalPlusUn(team2);
					setNombreMatchsGagnesBanalExterieurPlusUn(team2);
				}
				// Si E2 gagne et le match est important pour E1:
				// E1 nombre de matchs perdus importants +1 gen et dom
				if (importantE1) {
					setNombreMatchsPerdusImportantsPlusUn(team1);
					setNombreMatchsPerdusImportantsDomicilePlusUn(team1);
				}
				// Si E2 gagne et le match est banal pour E1 :
				// E1 nombre de matchs perdus banal +1 gen et dom
				else {
					setNombreMatchsPerdusBanalPlusUn(team1);
					setNombreMatchsPerdusBanalDomicilePlusUn(team1);
				}
			}
			// Match nul
			else {
				add1point(team1);
				add1point(team2);
				add1pointDomicile(team1);
				add1pointExterieur(team2);
				// gère les VNDVV
				addSerieEnCours(team1, "N");
				addSerieEnCours(team2, "N");
				addSerieEnCoursDomicile(team1, "N");
				addSerieEnCoursExterieur(team2, "N");
				// gere les V:3 N:1 D:1
				setNombreMatchsNulsPlusUn(team1);
				setNombreMatchsNulsPlusUn(team2);
				setNombreMatchsNulsExterieurPlusUn(team2);
				setNombreMatchsNulsDomicilePlusUn(team1);
				// Si E2 est mieux classée :
				// E2 nombre matchs nuls contre moins bien classée +1 gen et ext
				// E1 nombre matchs nuls contre mieux classée +1 gen et dom
				if (!e1mieuxClassee) {
					setNombreMatchsNulsContreEquipeMoinsBienClasseePlusUn(team2);
					setNombreMatchsNulsContreEquipeMieuxClasseePlusUn(team1);
					setNombreMatchsNulsContreEquipeMoinsBienClasseeExterieurPlusUn(team2);
					setNombreMatchsNulsContreEquipeMieuxClasseeDomicilePlusUn(team1);
				}
				// Si E2 est moins bien classée :
				// E2 nombre matchs nuls contre mieux classée +1 gen et ext
				// E1 nombre matchs nuls contre moins bien classée +1 gen et dom
				else {
					setNombreMatchsNulsContreEquipeMieuxClasseePlusUn(team2);
					setNombreMatchsNulsContreEquipeMoinsBienClasseePlusUn(team1);
					setNombreMatchsNulsContreEquipeMieuxClasseeExterieurPlusUn(team2);
					setNombreMatchsNulsContreEquipeMoinsBienClasseeDomicilePlusUn(team1);
				}
				// Si le match est important pour E2 :
				// E2 nombre de matchs nuls importants +1 gen et ext
				if (importantE2) {
					setNombreMatchsNulsImportantsPlusUn(team2);
					setNombreMatchsNulsImportantsExterieurPlusUn(team2);
				}
				// Si le match est banal pour E2:
				// E2 nombre de matchs nuls banal +1 gen et ext
				else {
					setNombreMatchsNulsBanalPlusUn(team2);
					setNombreMatchsNulsBanalExterieurPlusUn(team2);
				}
				// Si le match est important pour E1:
				// E1 nombre de matchs nuls importants +1 gen et dom
				if (importantE1) {
					setNombreMatchsNulsImportantsPlusUn(team1);
					setNombreMatchsNulsImportantsDomicilePlusUn(team1);
				}
				// Si le match est banal pour E1 :
				// E1 nombre de matchs nuls banal +1 gen et dom
				else {
					setNombreMatchsNulsBanalPlusUn(team1);
					setNombreMatchsNulsBanalDomicilePlusUn(team1);
				}
			}
		}
		setSerieEnCours(team1);
		setSerieEnCours(team2);
		setSerieEnCoursDomicile(team1);
		setSerieEnCoursExterieur(team2);
		boolean verifE1 = TeamController.verif(team1);
		boolean verifE2 = TeamController.verif(team2);
		boolean allVerifs = verifE1 && verifE2;
		if (allVerifs) {
			DatabaseConnection.createOrUpdateTeam(team1);
			DatabaseConnection.createOrUpdateTeam(team2);
		}
		return (allVerifs);
	}

	public static void setNombreMatchsJouesPlusUn(Team team1) {
		team1.setNbMatchesPlayed(team1.getNbMatchesPlayed() + 1);
	}

	private static void setGoalDifferenceIn3Standings(Team team1, Team team2, String score) {
		String[] scoreTableau = score.split("-");
		int goalDifferenceInFavorOfHomeTeam = Integer.parseInt(scoreTableau[0]) - Integer.parseInt(scoreTableau[1]);
		int goalDifferenceInFavorOfAwayTeam = Integer.parseInt(scoreTableau[1]) - Integer.parseInt(scoreTableau[0]);
		updateGlobalGoalAverage(team1, team2, goalDifferenceInFavorOfHomeTeam, goalDifferenceInFavorOfAwayTeam);
		updateHomeAndAwayGoalAverage(team1, team2, goalDifferenceInFavorOfHomeTeam, goalDifferenceInFavorOfAwayTeam);
	}

	private static void updateHomeAndAwayGoalAverage(Team team1, Team team2, int differenceEnFaveurEquipeDomicile,
			int differenceEnFaveurEquipeExterieur) {
		team1.setHomeGoalAverage(team1.getHomeGoalAverage() + differenceEnFaveurEquipeDomicile);
		team2.setAwayGoalAverage(team2.getAwayGoalAverage() + differenceEnFaveurEquipeExterieur);
	}

	private static void updateGlobalGoalAverage(Team team1, Team team2, int differenceEnFaveurEquipeDomicile,
			int differenceEnFaveurEquipeExterieur) {
		team1.setGoalAverage(team1.getGoalAverage() + differenceEnFaveurEquipeDomicile);
		team2.setGoalAverage(team2.getGoalAverage() + differenceEnFaveurEquipeExterieur);
	}

	// LES SERIES

	/**
	 * Prend en compte le dernier résultat et modifie les 5 derniers résultats de
	 * l'équipe exemple : "VVDVV" + "N" ==> "VDVVN" on décale vers la gauche
	 * 
	 * @param team2 @
	 */
	private static void addSerieEnCoursExterieur(Team team2, String lastAwayResult) {
		team2.setAwayRecent5(team2.getAwayRecent4());
		team2.setAwayRecent4(team2.getAwayRecent3());
		team2.setAwayRecent3(team2.getAwayRecent2());
		team2.setAwayRecent2(team2.getAwayRecent1());
		team2.setAwayRecent1(lastAwayResult);
	}

	private static void addSerieEnCoursDomicile(Team team1, String lastHomeResult) {
		team1.setHomeRecent5(team1.getHomeRecent4());
		team1.setHomeRecent4(team1.getHomeRecent3());
		team1.setHomeRecent3(team1.getHomeRecent2());
		team1.setHomeRecent2(team1.getHomeRecent1());
		team1.setHomeRecent1(lastHomeResult);
	}

	private static void addSerieEnCours(Team team, String lastResult) {
		team.setRecent5(team.getRecent4());
		team.setRecent4(team.getRecent3());
		team.setRecent3(team.getRecent2());
		team.setRecent2(team.getRecent1());
		team.setRecent1(lastResult);
	}

	private static void setSerieEnCours(Team team) {

		int countNombreVictoiresConsecutives = 0;
		int countNombreNulsConsecutifs = 0;
		int countNombreDefaitesConsecutives = 0;
		Boolean isAWinningStreakSeries = false;
		Boolean isADrawStreakSeries = false;
		Boolean isALoosingStreakSeries = false;

		if (team.getRecent1().equals("V")) {
			isAWinningStreakSeries = true;
			countNombreVictoiresConsecutives++;
			team.setDrawSerie(0);
			team.setLoosingSerie(0);
		}
		if (team.getRecent1().equals("N")) {
			isADrawStreakSeries = true;
			countNombreNulsConsecutifs++;
			team.setWinningSerie(0);
			team.setLoosingSerie(0);
		}
		if (team.getRecent1().equals("D")) {
			isALoosingStreakSeries = true;
			countNombreDefaitesConsecutives++;
			team.setWinningSerie(0);
			team.setDrawSerie(0);
		}
		if (isAWinningStreakSeries) {
			updateSerie(team, countNombreVictoiresConsecutives, "V");
		}
		if (isADrawStreakSeries) {
			updateSerie(team, countNombreNulsConsecutifs, "N");
		}
		if (isALoosingStreakSeries) {
			updateSerie(team, countNombreDefaitesConsecutives, "D");
		}
	}

	private static void setSerieEnCoursDomicile(Team team1) {

		int countNombreVictoiresConsecutives = 0;
		int countNombreNulsConsecutifs = 0;
		int countNombreDefaitesConsecutives = 0;
		Boolean isAWinningStreakSeries = false;
		Boolean isADrawStreakSeries = false;
		Boolean isALoosingStreakSeries = false;

		if (team1.getHomeRecent1().equals("V")) {
			isAWinningStreakSeries = true;
			countNombreVictoiresConsecutives++;
			team1.setHomeDrawSerie(0);
			team1.setHomeLoosingSerie(0);
		}
		if (team1.getHomeRecent1().equals("N")) {
			isADrawStreakSeries = true;
			countNombreNulsConsecutifs++;
			team1.setHomeLoosingSerie(0);
			team1.setHomeWinningSerie(0);
		}
		if (team1.getHomeRecent1().equals("D")) {
			isALoosingStreakSeries = true;
			countNombreDefaitesConsecutives++;
			team1.setHomeDrawSerie(0);
			team1.setHomeWinningSerie(0);
		}
		if (isAWinningStreakSeries) {
			updateHomeSerie(team1, countNombreVictoiresConsecutives, "V");
		}
		if (isADrawStreakSeries) {
			updateHomeSerie(team1, countNombreNulsConsecutifs, "N");
		}
		if (isALoosingStreakSeries) {
			updateHomeSerie(team1, countNombreDefaitesConsecutives, "D");
		}
	}

	private static void setSerieEnCoursExterieur(Team team2) {

		int countNombreVictoiresConsecutives = 0;
		int countNombreNulsConsecutifs = 0;
		int countNombreDefaitesConsecutives = 0;
		Boolean isAWinningStreakSeries = false;
		Boolean isADrawStreakSeries = false;
		Boolean isALoosingStreakSeries = false;

		if (team2.getAwayRecent1().equals("V")) {
			isAWinningStreakSeries = true;
			countNombreVictoiresConsecutives++;
			team2.setAwayDrawSerie(0);
			team2.setAwayLoosingSerie(0);
		}
		if (team2.getAwayRecent1().equals("N")) {
			isADrawStreakSeries = true;
			countNombreNulsConsecutifs++;
			team2.setAwayWinningSerie(0);
			team2.setAwayLoosingSerie(0);
		}
		if (team2.getAwayRecent1().equals("D")) {
			isALoosingStreakSeries = true;
			countNombreDefaitesConsecutives++;
			team2.setAwayDrawSerie(0);
			team2.setAwayWinningSerie(0);
		}
		if (isAWinningStreakSeries) {
			updateAwaySerie(team2, countNombreVictoiresConsecutives, "V");
		}
		if (isADrawStreakSeries) {
			updateAwaySerie(team2, countNombreNulsConsecutifs, "N");
		}
		if (isALoosingStreakSeries) {
			updateAwaySerie(team2, countNombreDefaitesConsecutives, "D");
		}
	}

	private static void updateSerie(Team team, int serieEnCours, String resultat) {

		Boolean isSeriesAlive = true;
		if (team.getRecent2().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getRecent3().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getRecent4().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getRecent5().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (resultat.equals("V"))
			team.setWinningSerie(serieEnCours);
		if (resultat.equals("N"))
			team.setDrawSerie(serieEnCours);
		if (resultat.equals("D"))
			team.setLoosingSerie(serieEnCours);
	}

	private static void updateHomeSerie(Team team, int serieEnCours, String resultat) {

		Boolean isSeriesAlive = true;
		if (team.getHomeRecent2().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getHomeRecent3().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getHomeRecent4().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getHomeRecent5().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (resultat.equals("V"))
			team.setHomeWinningSerie(serieEnCours);
		if (resultat.equals("N"))
			team.setHomeDrawSerie(serieEnCours);
		if (resultat.equals("D"))
			team.setHomeLoosingSerie(serieEnCours);
	}

	private static void updateAwaySerie(Team team, int serieEnCours, String resultat) {

		Boolean isSeriesAlive = true;
		if (team.getAwayRecent2().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getAwayRecent3().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getAwayRecent4().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (isSeriesAlive && team.getAwayRecent5().equals(resultat)) {
			serieEnCours++;
		} else {
			isSeriesAlive = false;
		}
		if (resultat.equals("V"))
			team.setAwayWinningSerie(serieEnCours);
		if (resultat.equals("N"))
			team.setAwayDrawSerie(serieEnCours);
		if (resultat.equals("D"))
			team.setAwayLoosingSerie(serieEnCours);
	}

	private static void setNombreMatchsJouesContreEquipeMoinsBienClasseeExterieurPlusUn(Team team2) {
		team2.setNbMatchesPlayedAgainstStandingInferiorAway(team2.getNbMatchesPlayedAgainstStandingInferiorAway() + 1);
	}

	private static void setNombreMatchsJouesContreEquipeMieuxClasseeDomicilePlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstStandingSuperiorAtHome(
				team1.getNbMatchesPlayedAgainstStandingSuperiorAtHome() + 1);
	}

	private static void setNombreMatchsJouesContreEquipeMoinsBienClasseeDomicilePlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstStandingInferiorAtHome(
				team1.getNbMatchesPlayedAgainstStandingInferiorAtHome() + 1);
	}

	private static void setNombreMatchsJouesContreEquipeMieuxClasseeExterieurPlusUn(Team team2) {
		team2.setNbMatchesPlayedAgainstStandingSuperiorAway(team2.getNbMatchesPlayedAgainstStandingSuperiorAway() + 1);
	}

	private static void setNombreMatchsNulsBanalDomicilePlusUn(Team team1) {
		team1.setNbDrawsAgainstNormalOpponentAtHome(team1.getNbDrawsAgainstNormalOpponentAtHome() + 1);
	}

	private static void setNombreMatchsNulsImportantsDomicilePlusUn(Team team1) {
		team1.setNbDrawsAgainstImportantOpponentAtHome(team1.getNbDrawsAgainstImportantOpponentAtHome() + 1);
	}

	private static void setNombreMatchsNulsBanalExterieurPlusUn(Team team2) {
		team2.setNbDrawsAgainstNormalOpponentAway(team2.getNbDrawsAgainstNormalOpponentAway() + 1);
	}

	private static void setNombreMatchsNulsBanalPlusUn(Team team2) {
		team2.setNbDrawsAgainstNormalOpponent(team2.getNbDrawsAgainstNormalOpponent() + 1);
	}

	private static void setNombreMatchsNulsImportantsExterieurPlusUn(Team team2) {
		team2.setNbDrawsAgainstImportantOpponentAway(team2.getNbDrawsAgainstImportantOpponentAway() + 1);
	}

	private static void setNombreMatchsNulsImportantsPlusUn(Team team2) {
		team2.setNbDrawsAgainstImportantOpponent(team2.getNbDrawsAgainstImportantOpponent() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMoinsBienClasseeDomicilePlusUn(Team team1) {
		team1.setNbDrawsAgainstStandingInferiorAtHome(team1.getNbDrawsAgainstStandingInferiorAtHome() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMieuxClasseeExterieurPlusUn(Team team2) {
		team2.setNbDrawsAgainstStandingSuperiorAway(team2.getNbDrawsAgainstStandingSuperiorAway() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMieuxClasseeDomicilePlusUn(Team team1) {
		team1.setNbDrawsAgainstStandingSuperiorAtHome(team1.getNbDrawsAgainstStandingSuperiorAtHome() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMoinsBienClasseeExterieurPlusUn(Team team2) {
		team2.setNbDrawsAgainstStandingInferiorAway(team2.getNbDrawsAgainstStandingInferiorAway() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMieuxClasseePlusUn(Team team2) {
		team2.setNbDrawsAgainstStandingSuperior(team2.getNbDrawsAgainstStandingSuperior() + 1);
	}

	private static void setNombreMatchsNulsContreEquipeMoinsBienClasseePlusUn(Team team2) {
		team2.setNbDrawsAgainstStandingInferior(team2.getNbDrawsAgainstStandingInferior() + 1);
	}

	private static void setNombreMatchsNulsDomicilePlusUn(Team team1) {
		team1.setNbHomeDraws(team1.getNbHomeDraws() + 1);
	}

	private static void setNombreMatchsNulsExterieurPlusUn(Team team2) {
		team2.setNbAwayDraws(team2.getNbAwayDraws() + 1);
	}

	private static void setNombreMatchsNulsPlusUn(Team team1) {
		team1.setNbDraws(team1.getNbDraws() + 1);
	}

	private static void add1pointExterieur(Team team2) {
		team2.setNbAwayPoints(team2.getNbAwayPoints() + 1);
	}

	private static void add1pointDomicile(Team team1) {
		team1.setNbHomePoints(team1.getNbHomePoints() + 1);
	}

	private static void add1point(Team team1) {
		team1.setNbPoints(team1.getNbPoints() + 1);
	}

	private static void setNombreMatchsPerdusBanalDomicilePlusUn(Team team1) {
		team1.setNbLossesAgainstNormalOpponentAtHome(team1.getNbLossesAgainstNormalOpponentAtHome() + 1);
	}

	private static void setNombreMatchsPerdusImportantsDomicilePlusUn(Team team1) {
		team1.setNbLossesAgainstImportantOpponentAtHome(team1.getNbLossesAgainstImportantOpponentAtHome() + 1);
	}

	private static void setNombreMatchsGagnesBanalExterieurPlusUn(Team team2) {
		team2.setNbWinsAgainstNormalOpponentAway(team2.getNbWinsAgainstNormalOpponentAway() + 1);
	}

	private static void setNombreMatchsGagnesImportantsExterieurPlusUn(Team team2) {
		team2.setNbWinsAgainstImportantOpponentAway(team2.getNbWinsAgainstImportantOpponentAway() + 1);
	}

	private static void setNombreMatchsPerdusContreEquipeMoinsBienClasseeDomicilePlusUn(Team team1) {
		team1.setNbLossesAgainstStandingInferiorAtHome(team1.getNbLossesAgainstStandingInferiorAtHome() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMieuxClasseeExterieurPlusUn(Team team2) {
		team2.setNbWinsAgainstStandingSuperiorAway(team2.getNbWinsAgainstStandingSuperiorAway() + 1);
	}

	private static void setNombreMatchsPerdusContreEquipeMieuxClasseeDomicilePlusUn(Team team1) {
		team1.setNbLossesAgainstStandingSuperiorAtHome(team1.getNbLossesAgainstStandingSuperiorAtHome() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMoinsBienClasseeExterieurPlusUn(Team team2) {
		team2.setNbWinsAgainstStandingInferiorAway(team2.getNbWinsAgainstStandingInferiorAway() + 1);
	}

	private static void setNombreMatchsPerdusDomicilePlusUn(Team team1) {
		team1.setNbHomeLosses(team1.getNbHomeLosses() + 1);
	}

	private static void setNombreMatchsGagnesExterieurPlusUn(Team team2) {
		team2.setNbAwayWins(team2.getNbAwayWins() + 1);
	}

	private static void add3pointsExterieur(Team team2) {
		team2.setNbAwayPoints(team2.getNbAwayPoints() + 3);
	}

	private static void setNombreMatchsPerdusBanalExterieurPlusUn(Team team2) {
		team2.setNbLossesAgainstNormalOpponentAway(team2.getNbLossesAgainstNormalOpponentAway() + 1);
	}

	private static void setNombreMatchsPerdusBanalPlusUn(Team team2) {
		team2.setNbLossesAgainstNormalOpponent(team2.getNbLossesAgainstNormalOpponent() + 1);
	}

	private static void setNombreMatchsPerdusImportantsExterieurPlusUn(Team team2) {
		team2.setNbLossesAgainstImportantOpponentAway(team2.getNbLossesAgainstImportantOpponentAway() + 1);
	}

	private static void setNombreMatchsPerdusImportantsPlusUn(Team team2) {
		team2.setNbLossesAgainstImportantOpponent(team2.getNbLossesAgainstImportantOpponent() + 1);
	}

	private static void setNombreMatchsGagnesBanalDomicilePlusUn(Team team1) {
		team1.setNbWinsAgainstNormalOpponentAtHome(team1.getNbWinsAgainstNormalOpponentAtHome() + 1);
	}

	private static void setNombreMatchsGagnesBanalPlusUn(Team team1) {
		team1.setNbWinsAgainstNormalOpponent(team1.getNbWinsAgainstNormalOpponent() + 1);
	}

	private static void setNombreMatchsGagnesImportantsDomicilePlusUn(Team team1) {
		team1.setNbWinsAgainstImportantOpponentAtHome(team1.getNbWinsAgainstImportantOpponentAtHome() + 1);
	}

	private static void setNombreMatchsGagnesImportantsPlusUn(Team team1) {
		team1.setNbWinsAgainstImportantOpponent(team1.getNbWinsAgainstImportantOpponent() + 1);
	}

	private static void setNombreMatchsPerdusContreEquipeMoinsBienClasseeExterieurPlusUn(Team team2) {
		team2.setNbLossesAgainstStandingInferiorAway(team2.getNbLossesAgainstStandingInferiorAway() + 1);
	}

	private static void setNombreMatchsPerdusContreEquipeMoinsBienClasseePlusUn(Team team2) {
		team2.setNbLossesAgainstStandingInferior(team2.getNbLossesAgainstStandingInferior() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMieuxClasseeDomicilePlusUn(Team team1) {
		team1.setNbWinsAgainstStandingSuperiorAtHome(team1.getNbWinsAgainstStandingSuperiorAtHome() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMieuxClasseePlusUn(Team team1) {
		team1.setNbWinsAgainstStandingSuperior(team1.getNbWinsAgainstStandingSuperior() + 1);
	}

	private static void add3pointsDomicile(Team team1) {
		team1.setNbHomePoints(team1.getNbHomePoints() + 3);
	}

	private static void add3points(Team team1) {
		team1.setNbPoints(team1.getNbPoints() + 3);
	}

	private static void setNombreMatchsPerdusContreEquipeMieuxClasseeExterieurPlusUn(Team team2) {
		team2.setNbLossesAgainstStandingSuperiorAway(team2.getNbLossesAgainstStandingSuperiorAway() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMoinsBienClasseeDomicilePlusUn(Team team1) {
		team1.setNbWinsAgainstStandingInferiorAtHome(team1.getNbWinsAgainstStandingInferiorAtHome() + 1);
	}

	private static void setNombreMatchsPerdusContreEquipeMieuxClasseePlusUn(Team team2) {
		team2.setNbLossesAgainstStandingSuperior(team2.getNbLossesAgainstStandingSuperior() + 1);
	}

	private static void setNombreMatchsGagnesContreEquipeMoinsBienClasseePlusUn(Team team1) {
		team1.setNbWinsAgainstStandingInferior(team1.getNbWinsAgainstStandingInferior() + 1);
	}

	private static void setNombreMatchsPerdusExterieurPlusUn(Team team2) {
		team2.setNbAwayLosses(team2.getNbAwayLosses() + 1);
	}

	private static void setNombreMatchsPerdusPlusUn(Team team2) {
		team2.setNbLosses(team2.getNbLosses() + 1);
	}

	private static void setNombreMatchsGagnesDomicilePlusUn(Team team1) {
		team1.setNbHomeWins(team1.getNbHomeWins() + 1);
	}

	private static void setNombreMatchsGagnesPlusUn(Team team1) {
		team1.setNbWins(team1.getNbWins() + 1);
	}

	private static void setNombreMatchsJouesBanalExterieurPlusUn(Team team2) {
		team2.setNbMatchesPlayedAgainstNormalOpponentAway(team2.getNbMatchesPlayedAgainstNormalOpponentAway() + 1);
	}

	private static void setNombreMatchsJouesImportantsExterieurPlusUn(Team team2) {
		team2.setNbMatchesPlayedAgainstImportantOpponentAway(
				team2.getNbMatchesPlayedAgainstImportantOpponentAway() + 1);
	}

	private static void setNombreMatchsJouesBanalDomicilePlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstNormalOpponentAtHome(team1.getNbMatchesPlayedAgainstNormalOpponentAtHome() + 1);
	}

	private static void setNombreMatchsJouesBanalPlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstNormalOpponent(team1.getNbMatchesPlayedAgainstNormalOpponent() + 1);
	}

	private static void setNombreMatchsJouesImportantsDomicilePlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstImportantOpponentAtHome(
				team1.getNbMatchesPlayedAgainstImportantOpponentAtHome() + 1);
	}

	private static void setNombreMatchsJouesImportantsPlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstImportantOpponent(team1.getNbMatchesPlayedAgainstImportantOpponent() + 1);
	}

	private static void setNombreMatchsJouesContreEquipeMoinsBienClasseePlusUn(Team team1) {
		team1.setNbMatchesPlayedAgainstStandingInferior(team1.getNbMatchesPlayedAgainstStandingInferior() + 1);
	}

	private static void setNombreMatchsJouesContreEquipeMieuxClasseePlusUn(Team team2) {
		team2.setNbMatchesPlayedAgainstStandingSuperior(team2.getNbMatchesPlayedAgainstStandingSuperior() + 1);
	}

	private static void setNombreMatchsJouesExterieurPlusUn(Team team2) {
		team2.setNbAwayMatchesPlayed(team2.getNbAwayMatchesPlayed() + 1);
	}

	private static void setNombreMatchsJouesDomicilePlusUn(Team team1) {
		team1.setNbHomeMatchesPlayed(team1.getNbHomeMatchesPlayed() + 1);
	}

	public static String getEquipeMieuxClassee(Team e1, Team e2) {
		String equipeMieuxClassee = null;
		if (e1 != null && e2 != null) {
			int c1 = e1.getStanding();
			int c2 = e2.getStanding();
			if (c1 < c2) {
				equipeMieuxClassee = e1.getNickName();
			} else {
				equipeMieuxClassee = e2.getNickName();
			}
		}
		return equipeMieuxClassee;
	}

	public static boolean isEmpty(String string) {
		return (string == null || string.isEmpty());
	}

	/**
	 * @param e1
	 * @param e2
	 * @return true si c'est un match important pour e1, false sinon.
	 */
	public static boolean getMatchImportant(String e1, String e2) {
		String rivalsE1;
		try {
			rivalsE1 = DatabaseConnection.getRivals(e1);
		} catch (NullRivalException e) {
			reportError(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return rivalsE1.contains(e2);
	}

	public static boolean convertToBoolean(int number) throws InvalidNumberToConvertFromBooleanException {
		if (number < 0 || number > 1) {
			throw new InvalidNumberToConvertFromBooleanException("Erreur à la conversion de la valeur numérique "
					+ number + " en un booléen. La valeur doit être comprise entre 0 et 1 inclus.");
		}
		if (number == 0)
			return false;
		else
			return true;
	}

	public static void createSecondLeg() throws NullConfrontationException {
		Collection<Confrontation> allConfrontations = DatabaseConnection.getAllConfrontations();
		if (allConfrontations != null && !allConfrontations.isEmpty()) {
			Iterator<Confrontation> it = allConfrontations.iterator();
			while (it.hasNext()) {
				Confrontation confrontation = it.next();
				Confrontation reversedConfrontation = getReversedConfrontation(confrontation);
				if (reversedConfrontation == null) {
					throw new NullConfrontationException("Erreur à la création de la confrontation inversée.");
				}
				DatabaseConnection.createOrUpdateConfrontation(reversedConfrontation);
			}
		} else {
			throw new NullConfrontationException(
					"Aucune confrontation n'existe actuellement. Impossible de créer la phse retour.");
		}
	}

	public static Confrontation getReversedConfrontation(Confrontation confrontation) {
		String originalMatch = confrontation.getMatch();
		String reversedMatch = switchValues(originalMatch);
		String originalRecent1 = confrontation.getRecent1();
		String reversedRecent1 = "";
		if (!Ligue1Utils.isEmpty(originalRecent1) && !originalRecent1.equals("null")) {
			reversedRecent1 = switchValues(originalRecent1);
		}
		String originalRecent2 = confrontation.getRecent2();
		String reversedRecent2 = "";
		if (!Ligue1Utils.isEmpty(originalRecent2) && !originalRecent2.equals("null")) {
			reversedRecent2 = switchValues(originalRecent2);
		}
		String originalRecent3 = confrontation.getRecent3();
		String reversedRecent3 = "";
		if (!Ligue1Utils.isEmpty(originalRecent3) && !originalRecent3.equals("null")) {
			reversedRecent3 = switchValues(originalRecent3);
		}
		String originalRecent4 = confrontation.getRecent4();
		String reversedRecent4 = "";
		if (!Ligue1Utils.isEmpty(originalRecent4) && !originalRecent4.equals("null")) {
			reversedRecent4 = switchValues(originalRecent4);
		}
		String originalRecent5 = confrontation.getRecent5();
		String reversedRecent5 = "";
		if (!Ligue1Utils.isEmpty(originalRecent5) && !originalRecent5.equals("null")) {
			reversedRecent5 = switchValues(originalRecent5);
		}
		Confrontation reversedConfrontation = new Confrontation(reversedMatch, reversedRecent1, reversedRecent2,
				reversedRecent3, reversedRecent4, reversedRecent5);
		return reversedConfrontation;
	}

	private static String switchValues(String originalMatch) {
		String[] split = originalMatch.split("-");
		String reversed = split[1] + "-" + split[0];
		return reversed;
	}

	/**
	 * @param nb, the value to evaluate
	 * @return a percentage representing nb/nbConfrontations * 100
	 */
	public static Float percentage(int nb) {
		int matchesCounted = DatabaseConnection.getMatchesCounted();
		if (matchesCounted != 0) {
			return ((float) nb / matchesCounted) * 100;
		}
		return null;
	}

	public static void main(String[] args) {
	}

	public static void reportInfo(String log) {
		System.out.println("[INFO] : " + log);
	}

	public static void reportError(String log) {
		System.out.println("[ERROR] : " + log);
		InitializeWindow.alertError(log);
	}

	public static boolean isAllowed(int standing) {
		return (standing > 0 && standing < 21);
	}

	public static String[] getCurrentJournees() {
		String currentJournees = "";
		Collection<Match> matchesToCount = DatabaseConnection.getMatchesToCount();
		Iterator<Match> iterator = matchesToCount.iterator();
		while (iterator.hasNext()) {
			Match match = iterator.next();
			if (Ligue1Utils.isEmpty(currentJournees)) {
				currentJournees = currentJournees+match.getJourney();
			}
			else {
				currentJournees = currentJournees+";"+match.getJourney();
			}
		}
		return currentJournees.split(";");
	}

	public static String convert01ToString(String text) {
		if (text.equals("0")) {
			return "Non";
		}
		if (text.equals("1")) {
			return "Oui";
		}
		return "";
	}

	public static Integer convertStringTo01(String text) {
		if (text.equals("Non")) {
			return 0;
		}
		if (text.equals("Oui")) {
			return 1;
		}
		return null;
	}

	public static String convert01ToString(int i) {
		if (i==0) {
			return "Non";
		}
		if (i==1) {
			return "Oui";
		}
		return "";
	}

}
