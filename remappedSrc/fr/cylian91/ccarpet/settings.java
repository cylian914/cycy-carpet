package fr.cylian91.ccarpet;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.COMMAND;

public class settings {
    @Rule(
            categories = {COMMAND,"Supression"},
            options={"0","1","2"}
    )
    public static int LogUpdateInteration = 0;
    @Rule(
            categories = {COMMAND,"Supression"},
            options = {"1000000","-1","0"},
            strict = false
    )
    public static int MaxUpdateChain = -1;
    @Rule(
            categories = {COMMAND,"Supression"},
            options = {"2147483641","0"},
            strict = false
    )
    public static int UpdateInterationValue = 0;
    @Rule(
            categories = {COMMAND,"Supression"}
    )
    public static boolean SendSupressingErrorToChat = false;

}
