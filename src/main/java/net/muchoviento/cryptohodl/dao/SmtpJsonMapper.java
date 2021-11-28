package net.muchoviento.cryptohodl.dao;

import net.muchoviento.cryptohodl.controller.SmtpDto;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;


public final class SmtpJsonMapper {

    private SmtpJsonMapper() {
        // util-class
    }

    private static final String SERVER = "server";

    private static final String PORT = "port";

    private static final String USER = "user";

    private static final String PASSWORD = "pwd";

    private static final String MAIL_TO = "mailTo";
    
    public static String toJSON(final SmtpDto smtpDto) {
        final JSONWriter jsonStringer = new JSONStringer();
        jsonStringer.object()
            .key(SERVER).value(smtpDto.getServer())
            .key(PORT).value(smtpDto.getPort())
            .key(USER).value(smtpDto.getUser())
            .key(PASSWORD).value(smtpDto.getPwd())
            .key(MAIL_TO).value(smtpDto.getMailTo())
            .endObject();
            return jsonStringer.toString();
    }

    public static SmtpDto toSmtpDto(final String asJson) {
        final JSONObject jsonObject = new JSONObject(asJson);
        final SmtpDto smtpDto = new SmtpDto();
        smtpDto.setServer(jsonObject.getString(SERVER));
        smtpDto.setPort(jsonObject.getString(PORT));
        smtpDto.setUser(jsonObject.getString(USER));
        smtpDto.setPwd(jsonObject.getString(PASSWORD));
        if (jsonObject.has(MAIL_TO)) {
            smtpDto.setMailTo(jsonObject.getString(MAIL_TO));
        }
        return smtpDto;
    }

}
