package net.muchoviento.cryptohodl.dao;

import net.muchoviento.cryptohodl.controller.SmtpDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmtpDao {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpDao.class);

    public void saveSmtp(final SmtpDto smtpDto) {
        final String asJSON = SmtpJsonMapper.toJSON(smtpDto);
        File file = new File("./data/smtp.json");
        file.getParentFile().mkdirs();
        byte[] strToBytes = asJSON.getBytes();
        try {
            Files.write(file.toPath(), strToBytes);
        } catch (IOException e) {
            LOG.error("error", e);
        }
    }
    
    public SmtpDto loadSmtp() {
        String json;
        try {
            json = Files.readString(Paths.get("./data/smtp.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new SmtpDto();
        }
        return SmtpJsonMapper.toSmtpDto(json);
    }

}
