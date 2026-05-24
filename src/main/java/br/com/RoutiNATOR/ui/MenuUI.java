package br.com.RoutiNATOR.ui;

import br.com.RoutiNATOR.util.Color;

public class MenuUI {
    public void logo() {
        System.out.flush();
        System.out.println("""
                  %s_____             _   _ _   _       _______ ____  _____ \s
                 |  __ \\           | | (_) \\ | |   /\\|__   __/ __ \\|  __ \\\s
                 | |__) |___  _   _| |_ _|  \\| |  /  \\  | | | |  | | |__) |
                 |  _  // _ \\| | | | __| | . ` | / /\\ \\ | | | |  | |  _  /\s
                 | | \\ \\ (_) | |_| | |_| | |\\  |/ ____ \\| | | |__| | | \\ \\\s
                 |_|  \\_\\___/ \\__,_|\\__|_|_| \\_/_/    \\_\\_|  \\____/|_|  \\_\\
                 ##########################################################%s
                """.formatted(Color.AZUL, Color.RESET));
    }
}
